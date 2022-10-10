package com.example.notesfirebase.adapter


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfirebase.R
import com.example.notesfirebase.databinding.ItemNotesBinding
import com.example.notesfirebase.fragments.EditNotesFragment
import com.example.notesfirebase.model.UserModel
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class NoteAdapter(
    val context: Context,
    options: FirestoreRecyclerOptions<UserModel>
) :
    FirestoreRecyclerAdapter<UserModel, NoteAdapter.NoteViewHolder>(options) {
    inner class NoteViewHolder(val binding: ItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            ItemNotesBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int, model: UserModel) {
        holder.binding.notesTitleTv.text = model.title
        holder.binding.notesDescriptionTv.text = model.note
        holder.binding.notesTimeTv.text = model.currentTime

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("title", model.title)
            bundle.putString("note", model.note)
            val docId = snapshots.getSnapshot(position).id
            bundle.putString("DocId", docId)
            Navigation.findNavController(it).navigate(R.id.action_noteFragment_to_editNotesFragment, bundle)
        }

    }
}