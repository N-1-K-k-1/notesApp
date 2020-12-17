package com.n1kk1.notesapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText

import com.n1kk1.notesapp.R
import com.n1kk1.notesapp.model.Note
import kotlinx.android.synthetic.main.edit_note_activity.*

class EditNoteActivity : AppCompatActivity() {

    var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note_activity)

        if (intent != null && intent.hasExtra("note")) {
            note = intent.getParcelableExtra("note")
            setData()
        }

        title = if (note != null) getString(R.string.view_edit_note) else getString(R.string.create_note)
    }

    // Save the note if it's not empty when the back button was pressed
    override fun onBackPressed() {
        if (edit_title.text?.isNotEmpty()!! && edit_description.text?.isNotEmpty()!!)
            saveNote()
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflate = menuInflater
        menuInflate.inflate(R.menu.save_note_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.save_note -> {
                saveNote()
            }
        }
        return true
    }

    private fun setData() {
        edit_title.setText(note?.title)
        edit_description.setText(note?.description)
    }

    private fun saveNote() {
        if (!fieldIsEmpty()) {
            val note = Note(note?.id, edit_title.text.toString(), edit_description.text.toString())
            val intent = Intent()
            intent.putExtra("note", note)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }
    }

    private fun fieldIsEmpty(): Boolean {
        if (edit_title.text?.isEmpty()!!) {
            title_text.error = getString(R.string.enter_title)
            edit_title.requestFocus()
            return true
        }
        if (edit_description.text?.isEmpty()!!) {
            description.error = getString(R.string.enter_content)
            edit_description.requestFocus()
            return true
        }
        return false
    }
}