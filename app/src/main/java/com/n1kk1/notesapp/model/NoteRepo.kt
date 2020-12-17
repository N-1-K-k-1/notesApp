package com.n1kk1.notesapp.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.n1kk1.notesapp.model.AppDatabase.Companion.getInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class NoteRepo(app: Application) {

    private val noteDao: NoteDao = getInstance(app.applicationContext).noteDao()
    private val notes: LiveData<List<Note>> = noteDao.getAllNotes()

    fun getAllNotes(): LiveData<List<Note>> {
        return notes
    }

    fun saveNote(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDao.insertNote(note)
            }
        }
    }

    fun updateNote(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDao.updateNote(note)
            }
        }
    }

    fun deleteNote(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDao.deleteNote(note)
            }
        }
    }

}