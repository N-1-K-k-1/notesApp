package com.n1kk1.notesapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Note::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {

        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME)
                    .build()
            }

            return instance!!
        }

        fun destroyDB() {
            instance = null
        }

        @Volatile
        private var instance: AppDatabase? = null
        private const val DB_NAME = "notesDb"
    }
}