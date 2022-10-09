package com.github.danishjamal104.notes.ui.fragment.note

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.github.danishjamal104.notes.R
import com.github.danishjamal104.notes.data.model.Note
import com.github.danishjamal104.notes.databinding.FragmentNoteBinding
import com.github.danishjamal104.notes.util.*
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class NoteFragment : Fragment(R.layout.fragment_note) {

    private lateinit var _binding: FragmentNoteBinding
    private val binding get() = _binding

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("MMM dd, yyyy")
    private val date get() = sdf.format(Date())

    private val viewModel: NoteViewModel by viewModels()

    private var noteId: Int? = null
    private var note: Note? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentNoteBinding.bind(view)

        noteId = arguments?.getInt(AppConstant.NOTE_ID_KEY, -1)

        setup()
    }

    private fun setup() {
        binding.date.text = date
        registerNoteState()
        registerClickEvents()
        noteId?.let {
            if(it==-1) {
                enableButtons()
                return
            }
            viewModel.setEvent(NoteEvent.GetNote(it))
        } ?: enableButtons()

    }

    private fun registerNoteState() {
        viewModel.noteSate.observe(viewLifecycleOwner) {
            when(it) {
                is NoteState.EventResult -> handleEventResult(it)
                NoteState.Loading -> handleLoading()
                is NoteState.GetNoteFailure -> longToast(it.reason)
                is NoteState.GetNoteSuccess -> handleFetchNoteSuccess(it.note)
            }
        }
    }

    private fun registerClickEvents() {
        binding.deleteButton.setOnClickListener {
            note?.let {
                viewModel.setEvent(NoteEvent.DeleteNote(it))
            } ?: clearText()
        }
        binding.saveButton.setOnClickListener {
            note?.let {
                updateNote(it)
            } ?: createNote()
        }
        binding.backButton.setOnClickListener {
            findNavController().navigate(R.id.action_noteFragment_to_homeFragment)
        }
        binding.note.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val newText = s.toString().trim()
                if(note != null) {
                    if(newText == note!!.value) {
                        binding.saveButton.gone()
                    } else {
                        binding.saveButton.visible()
                    }
                } else {
                    if(newText.isEmpty()) {
                        binding.saveButton.gone()
                    } else {
                        binding.saveButton.visible()
                    }
                }
            }

        })
    }

    private fun createNote() {
        hideKeyboard()
        val text = binding.note.text.toString().trim()
        if(text.isEmpty()) {
            shortToast("Note can't be empty")
            return
        }
        viewModel.setEvent(NoteEvent.CreateNote(text))
    }

    private fun updateNote(note: Note) {
        hideKeyboard()
        val newText = binding.note.text.toString()
        if(newText.trim() == note.value) {
            shortToast("No update")
            return
        }
        note.value = newText
        viewModel.setEvent(NoteEvent.UpdateNote(note))
    }

    private fun clearText() {
        binding.note.setText("")
    }

    private fun enableButtons() {
        binding.deleteButton.isClickable = true
        binding.saveButton.isClickable = true
    }

    private fun handleFetchNoteSuccess(note: Note) {
        enableButtons()
        this.note = note
        binding.note.setText(note.value)
    }

    private fun handleEventResult(event: NoteState.EventResult) {
        longToast(event.info)
        if(event.success) {
            findNavController().navigate(R.id.action_noteFragment_to_homeFragment)
        }
    }

    private fun handleLoading() {
        binding.progressBar.show()
        binding.deleteButton.isClickable = false
        binding.saveButton.isClickable = false
    }

}