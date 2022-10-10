package com.example.notesfirebase.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.notesfirebase.R
import com.example.notesfirebase.databinding.FragmentEditNotesBinding
import com.example.notesfirebase.model.UserModel
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class EditNotesFragment : Fragment() {

    private lateinit var binding: FragmentEditNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditNotesBinding.inflate(inflater, container, false)
        val title = arguments?.getString("title")
        val note = arguments?.getString("note")


        binding.etEditNoteTitle.setText(title)
        binding.etEditNotes.setText(note)
        editNote()
        binding.deleteButton.setOnClickListener {
//            deleteNote()
            val bottomSheet:BottomSheetDialog = BottomSheetDialog(requireContext(), R.style.BottomSheetStyle)
            bottomSheet.setContentView(R.layout.dialog_delete)
            bottomSheet.setCancelable(true)
            val yes = bottomSheet.findViewById<View>(R.id.tvDeleteYes)
            val no = bottomSheet.findViewById<View>(R.id.tvDeleteNo)

            yes?.setOnClickListener {
                deleteNote()
                bottomSheet.dismiss()
            }
            no?.setOnClickListener {
                bottomSheet.dismiss()
            }

            bottomSheet.show()
        }

        return binding.root
    }

    private fun deleteNote() {
        val docId = arguments?.getString("DocId")
        val reference = FirebaseFirestore.getInstance().collection("notes")
            .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
            .collection("myNotes").document(docId!!.toString())

        reference.delete().addOnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Note Deleted", Toast.LENGTH_SHORT).show()

            } else {
                Toast.makeText(
                    requireContext(),
                    "Note not Deleted, Something went wrong",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
            Navigation.findNavController(binding.root).navigate(R.id.action_editNotesFragment_to_noteFragment)

        }
    }

    private fun editNote() {
        binding.EditButtonSaveNotes.setOnClickListener {
            val title = binding.etEditNoteTitle.text.toString()
            val note = binding.etEditNotes.text.toString()
            val docId = arguments?.getString("DocId")
            if (title.isNotEmpty() && note.isNotEmpty()) {

                val data = UserModel(
                    note = note,
                    title = title
                )

                val reference = FirebaseFirestore.getInstance().collection("notes")
                    .document(FirebaseAuth.getInstance().currentUser?.uid.toString())
                    .collection("myNotes").document(docId!!.toString())

                reference.set(data).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(requireContext(), "Note Edited", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Note not Edited, Something went wrong",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                Navigation.findNavController(it)
                    .navigate(R.id.action_editNotesFragment_to_noteFragment)

            } else {
                if (title.isEmpty()) {
                    binding.etEditNoteTitle.error = "Title required"
                } else {
                    binding.etEditNotes.error = "Note required"
                }
            }
        }
    }
}