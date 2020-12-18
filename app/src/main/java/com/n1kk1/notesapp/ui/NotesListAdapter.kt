package com.n1kk1.notesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.n1kk1.notesapp.R
import com.n1kk1.notesapp.model.Note
import kotlinx.android.synthetic.main.note.view.*

class NotesListAdapter(
    private val itemClicked: (Note) -> Unit
) : RecyclerView.Adapter<NotesListAdapter.ViewHolder>() {

    private var items: List<Note> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.note, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.noteTitle.text = getItemAt(position).title
        holder.noteDescription.text = getItemAt(position).description
    }

    fun setItems(notes: List<Note>) {
        this.items = notes
        notifyDataSetChanged()
    }

    fun getItemAt(position: Int): Note {
        return items[position]
    }

    inner class ViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                itemClicked.invoke(getItemAt(adapterPosition))
            }
        }

        var noteTitle: TextView = itemView.note_title
        var noteDescription: TextView = itemView.note_description
    }

    interface Callbacks {
        fun onItemClicked(note: Note)
    }

}