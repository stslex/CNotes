package com.stslex.core_data_source.database

import com.stslex.core_data_source.base.BaseRoomCallback
import com.stslex.core_data_source.base.BaseRoomDatabase
import com.stslex.core_model.entity.NoteEntity
import dagger.Lazy
import kotlinx.coroutines.CoroutineScope
import java.util.*

class NoteDatabaseCallback(
    scope: CoroutineScope,
    noteRoomDatabase: Lazy<BaseRoomDatabase<NoteDao>>
) : BaseRoomCallback<NoteDao>(scope, noteRoomDatabase) {

    override suspend fun populateDatabase(dao: NoteDao) {
        val note = NoteEntity(0, INITIAL_TITLE, INITIAL_CONTENT, System.currentTimeMillis())
        dao.insertAllNotes(Collections.nCopies(10, note))
    }

    companion object {
        private const val INITIAL_TITLE: String = "example note"
        private const val INITIAL_CONTENT: String =
            "Android 12 foregrounds the design language of Material You through updated widget styles, a dynamic color system, custom shapes, and motion enhancements. \n" +
                    "\n" +
                    "Starting with Android 12 on Pixel devices, users can personalize their phone with dynamic color by generating unique color experiences from any wallpaper image. Extracted colors can be applied across the entire OS, from the notification shade to the lock screen, to volume controls, and more."
    }
}