package com.github.danishjamal104.notes.ui.fragment.home.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.danishjamal104.notes.R
import com.github.danishjamal104.notes.data.model.Note
import com.github.danishjamal104.notes.databinding.NoteListItemBinding
import com.github.danishjamal104.notes.util.gone
import com.github.danishjamal104.notes.util.visible
import java.text.SimpleDateFormat
import java.util.*

class NotesAdapter
constructor(val context: Context): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val _binding = NoteListItemBinding.bind(itemView)
        val binding get() = _binding

    }

    @SuppressLint("SimpleDateFormat")
    private val sdf = SimpleDateFormat("MMM dd, yyyy")
    private val data = mutableListOf<Note>()

    var emptyView: View? = null
    var itemClickListener: ItemClickListener<Note>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.note_list_item, parent, false)
        return NoteViewHolder(view)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding

        binding.note.text = item.value
        binding.date.text = sdf.format(Date(item.timestamp))

        val color = ContextCompat.getColor(context, getRandomColorInt())
        binding.root.setCardBackgroundColor(color)

        binding.root.setOnClickListener {
            itemClickListener?.onItemClicked(item, position, binding.root)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    fun addNote(note: Note) {
        data.add(note)
        notifyItemInserted(itemCount)
    }

    fun addNotes(notes: List<Note>) {
        val oldSize = itemCount
        data.addAll(notes)
        notifyItemRangeInserted(oldSize, itemCount)
        emptyView?.let {
            if(notes.isEmpty()) {
               it.visible()
            } else {
                it.gone()
            }
        }
    }

    fun clearAll() {
        val size = itemCount
        data.clear()
        notifyItemRangeRemoved(0, size)
        emptyView?.visible()
    }

    private fun getRandomColorInt(): Int {
        val colors = intArrayOf(R.color.orange, R.color.green, R.color.purple,
            R.color.grey, R.color.red, R.color.mustard, R.color.brown)
        return colors.random()
    }


}