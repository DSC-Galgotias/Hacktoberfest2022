package com.example.notesfirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.notesfirebase.R
import com.example.notesfirebase.adapter.NoteAdapter
import com.example.notesfirebase.databinding.FragmentCreateNotesBinding
import com.example.notesfirebase.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CreateNotesFragment : Fragment() {

    private lateinit var binding: FragmentCreateNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        saveNote()

        return binding.root
    }

    private fun saveNote() {
        binding.buttonSaveNotes.setOnClickListener {
            val title = binding.etNoteTitle.text.toString()
            val note = binding.etNotes.text.toString()
            if (title.isNotEmpty() && note.isNotEmpty()) {

                val data = UserModel(
                    note = note,
                    title = title
                )
                val reference = FirebaseFirestore.getInstance().collection("notes")
                    .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .collection("myNotes").document()

                reference.set(data).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "Note saved", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(requireContext(), "Note not saved", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
                Navigation.findNavController(it)
                    .navigate(R.id.action_createNotesFragment_to_noteFragment)

            } else {
                if (title.isEmpty()) {
                    binding.etNoteTitle.error = "Title required"
                }
                else {
                    binding.etNotes.error = "Note required"
                }
            }
        }
    }

}