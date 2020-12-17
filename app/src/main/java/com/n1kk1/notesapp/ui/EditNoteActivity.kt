package com.n1kk1.notesapp.ui

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText

import com.n1kk1.notesapp.R
import com.n1kk1.notesapp.model.Note
import kotlinx.android.synthetic.main.edit_note_activity.*

class EditNoteActivity : AppCompatActivity() {

    var note: Note? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_note_activity)

        if (intent == null && intent.hasExtra("note")) {
            note = intent.getParcelableExtra("note")
            setData()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (edit_title.text.isNotEmpty() && edit_description.text.isNotEmpty())
            saveNote()
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
            intent.putExtra("mode", intent.getStringExtra("mode"))
            setResult(Activity.RESULT_OK, intent)
            Log.e("saved", "yes")
            finish()
        }
    }

    private fun fieldIsEmpty(): Boolean {
        if (edit_title.text.isEmpty()) {
            title_text.error = getString(R.string.enter_title)
            edit_title.requestFocus()
            return true
        }
        if (edit_description.text.isEmpty()) {
            description.error = getString(R.string.enter_content)
            edit_description.requestFocus()
            return true
        }
        return false
    }
}