package com.n1kk1.notesapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.n1kk1.notesapp.R

class NotesListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.notes_list_activity)
    }
}