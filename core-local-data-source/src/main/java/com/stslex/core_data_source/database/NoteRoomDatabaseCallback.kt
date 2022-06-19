package com.stslex.core_data_source.database

import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.core_data_source.dao.NoteDao
import com.stslex.core_local_data_source.R
import com.stslex.core_model.model.NoteEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.koin.java.KoinJavaComponent.inject

class NoteRoomDatabaseCallback(
    private val scope: CoroutineScope,
    private val context: Context,
    private val dispatchers: AppDispatchers
) : RoomDatabase.Callback() {

    private val roomDatabase: NoteRoomDatabase by inject(NoteRoomDatabase::class.java)

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        roomDatabase.let { database ->
            scope.launch(dispatchers.io) { populateDatabase(database.noteDao()) }
        }
    }

    private suspend fun populateDatabase(noteDao: NoteDao) {
        noteDao.deleteAllNotes()
        val initialNote = NoteEntity(
            title = context.getString(R.string.database_initial_title),
            content = context.getString(R.string.database_initial_content),
            timestamp = System.currentTimeMillis()
        )
        noteDao.insertNote(initialNote)
    }
}