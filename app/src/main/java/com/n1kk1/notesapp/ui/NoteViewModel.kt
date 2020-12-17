package com.n1kk1.notesapp.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.n1kk1.notesapp.model.Note
import com.n1kk1.notesapp.model.NoteRepo

class NoteViewModel(app: Application) : AndroidViewModel(app) {

    private val repo: NoteRepo = NoteRepo(app)
    private val notes: LiveData<List<Note>> = repo.getAllNotes()

    fun getAllNotes(): LiveData<List<Note>> {
        return repo.getAllNotes()
    }

    fun saveNote(note: Note) {
        repo.saveNote(note)
    }

    fun updateNote(note: Note) {
        repo.updateNote(note)
    }

    fun deleteNote(note: Note) {
        repo.deleteNote(note)
    }
}