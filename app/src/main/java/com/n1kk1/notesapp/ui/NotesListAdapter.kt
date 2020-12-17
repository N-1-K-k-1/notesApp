package com.n1kk1.notesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.n1kk1.notesapp.R
import com.n1kk1.notesapp.model.Note
import kotlinx.android.synthetic.main.note.view.*

class NotesListAdapter(callbacks: Callbacks) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private var items: List<Note> = emptyList()
    private var itemClickListener: Callbacks = callbacks

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setItems(notes: List<Note>) {
        this.items = notes
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {

        fun bind(note: Note) {
            itemView.note_title.text = note.title
            itemView.note_description.text = note.description

            itemView.setOnClickListener {
                itemClickListener.onItemClicked(note)
            }

            itemView.note_delete.setOnClickListener {
                itemClickListener.onDeleteClicked(note)
            }

        }
    }

    interface Callbacks {
        fun onItemClicked(note: Note)
        fun onDeleteClicked(note: Note)
    }

}