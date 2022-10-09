package com.github.danishjamal104.notes.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.danishjamal104.notes.R
import com.github.danishjamal104.notes.data.model.Note
import com.github.danishjamal104.notes.databinding.FragmentHomeBinding
import com.github.danishjamal104.notes.ui.fragment.home.adapter.ItemClickListener
import com.github.danishjamal104.notes.ui.fragment.home.adapter.NotesAdapter
import com.github.danishjamal104.notes.util.*
import com.github.danishjamal104.notes.util.sharedpreference.UserPreferences
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), ItemClickListener<Note> {

    private lateinit var _binding: FragmentHomeBinding
    private val binding get() = _binding

    private val viewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var preferences: UserPreferences

    @Inject
    lateinit var adapter: NotesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentHomeBinding.bind(view)

        adapter.clearAll()
        if(!preferences.isAuthenticated()) {
            findNavController().navigate(R.id.action_homeFragment_to_authenticationFragment)
        } else {
            setup()
            viewModel.setEvent(HomeEvent.GetNotes)
        }

    }

    private fun setup() {
        setupRecyclerView()
        registerHomeState()
        registerClickEvents()
    }

    private fun setupRecyclerView() {
        adapter.emptyView = binding.illustration
        binding.noteList.layoutManager = LinearLayoutManager(requireContext())
        binding.noteList.setHasFixedSize(false)
        binding.noteList.adapter = adapter

        adapter.itemClickListener = this
    }

    private fun registerHomeState() {
        viewModel.authState.observe(viewLifecycleOwner) {
            when(it) {
                is HomeState.GetNotesFailure -> handleFailure(it.reason)
                is HomeState.GetNotesSuccess -> handleSuccess(it.notes)
                HomeState.Loading -> handleLoading()
            }
        }
    }

    private fun registerClickEvents() {
        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_noteFragment)
        }
        binding.actionButton.setOnClickListener {
            val title = getString(R.string.confirm)
            val message = getString(R.string.logout_alert_message)
            requireContext().showDefaultMaterialAlert(title, message) {
                logOut()
            }
        }
        binding.swipeRefresh.setOnRefreshListener {
            viewModel.setEvent(HomeEvent.GetNotes)
        }
    }

    private fun logOut() {
        googleSignInClient.signOut().addOnCompleteListener {
            preferences.revokeAuthentication()
            findNavController().navigate(R.id.action_homeFragment_to_authenticationFragment)
        }
    }

    private fun handleSuccess(notes: List<Note>) {
        if(binding.swipeRefresh.isRefreshing) {
            adapter.clearAll()
        }
        adapter.addNotes(notes)
        hideProgress()
    }

    private fun handleFailure(reason: String) {
        longToast(reason)
        hideProgress()
    }

    private fun showProgress() {
        binding.linearProgress.visible()
    }

    private fun hideProgress() {
        binding.linearProgress.invisible()
        binding.swipeRefresh.isRefreshing = false
    }

    private fun handleLoading() {
        showProgress()
    }

    override fun onItemClicked(item: Note, position: Int, view: View) {
        findNavController().navigate(R.id.action_homeFragment_to_noteFragment,
            bundleOf(AppConstant.NOTE_ID_KEY to item.id))
    }

}