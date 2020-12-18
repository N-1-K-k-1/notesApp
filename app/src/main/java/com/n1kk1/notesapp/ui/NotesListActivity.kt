package com.n1kk1.notesapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.n1kk1.notesapp.R
import com.n1kk1.notesapp.model.Note
import com.n1kk1.notesapp.utils.SwipeToDeleteCallback
import kotlinx.android.synthetic.main.notes_list_activity.*

class NotesListActivity : AppCompatActivity(), NotesListAdapter.Callbacks {

    private lateinit var viewModel: NoteViewModel
    private lateinit var adapter: NotesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_list_activity)

        title = getString(R.string.your_notes)

        note_list.layoutManager = LinearLayoutManager(this)
        adapter = NotesListAdapter(this)
        note_list.adapter= adapter

        viewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        viewModel.getAllNotes().observe(this, Observer {

                no_notes.visibility = View.INVISIBLE
                adapter.setItems(it)

        })

        ItemTouchHelper(object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.deleteNote(adapter.getItemAt(viewHolder.adapterPosition))
            }
        }).attachToRecyclerView(note_list)

        add_note.setOnClickListener {
            val intent = Intent(this, EditNoteActivity::class.java)
            startActivityForResult(intent, 1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            val note = data?.getParcelableExtra<Note>("note")!!

            // 1 — Save request code, 2 — update request code
            if (requestCode == 1) {
                viewModel.saveNote(note)
            }
            else if (requestCode == 2) {
               viewModel.updateNote(note)
            }
        }
    }

    override fun onItemClicked(note: Note) {
        val intent = Intent(this, EditNoteActivity::class.java)
        intent.putExtra("note", note)
        startActivityForResult(intent, 2)
    }
}