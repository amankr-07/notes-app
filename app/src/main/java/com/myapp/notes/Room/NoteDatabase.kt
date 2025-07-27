package com.myapp.notes.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.myapp.notes.Model.Note
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {

    abstract fun getNoteDao() : NoteDAO

    // singleton
    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context : Context, scope : CoroutineScope) : NoteDatabase {
            return INSTANCE ?: synchronized(this){

                val instance = Room.databaseBuilder(context.applicationContext,
                    NoteDatabase::class.java, "note_database")
                    .addCallback(NoteDatabaseCallBack(scope))
                    .build()

                INSTANCE = instance

                instance
            }
        }

    }

    private class NoteDatabaseCallBack(private val scope : CoroutineScope) : RoomDatabase.Callback(){

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)

            INSTANCE?.let { database ->

                scope.launch {

                    val noteDao = database.getNoteDao()

                    noteDao.insert(Note("Example Title", "Example Description"))

                }

            }

        }

    }

}