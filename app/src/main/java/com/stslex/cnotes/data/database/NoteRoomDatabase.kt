package com.stslex.cnotes.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stslex.cnotes.data.entity.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    private class NoteDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) { populateDatabase(database.noteDao()) }
            }
        }

        suspend fun populateDatabase(noteDao: NoteDao) = noteDao.deleteAllNotes()
    }

    companion object {

        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): NoteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(NoteDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}