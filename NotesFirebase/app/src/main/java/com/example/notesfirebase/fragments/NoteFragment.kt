package com.example.notesfirebase.fragments

import android.media.MediaCommunicationManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesfirebase.R
import com.example.notesfirebase.adapter.NoteAdapter
import com.example.notesfirebase.databinding.FragmentNoteBinding
import com.example.notesfirebase.model.UserModel
import com.example.notesfirebase.utlis.Reference
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.firestore.Query


class NoteFragment : Fragment() {

    private lateinit var binding: FragmentNoteBinding
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentNoteBinding.inflate(layoutInflater, container, false)


        binding.ButtonAddNotes.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_noteFragment_to_createNotesFragment)
        }

        val query =
            Reference.getReference()?.orderBy(
                "currentTime",
                Query.Direction.ASCENDING
            )

        val recyclerOptions = FirestoreRecyclerOptions.Builder<UserModel>()
            .setQuery(query!!, UserModel::class.java).build()


        noteAdapter = NoteAdapter(requireContext(), recyclerOptions)

        binding.rcvAllNotes.apply {
            this.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            this.adapter = noteAdapter
        }


        return binding.root

    }

    override fun onStart() {
        super.onStart()
        noteAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        noteAdapter.stopListening()
    }

    override fun onResume() {
        super.onResume()
        noteAdapter.notifyDataSetChanged()
    }

}