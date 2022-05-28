package com.stslex.core_data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.stslex.core_data_source.NoteRoomDatabase.Companion.SCHEMA_VERSION
import com.stslex.core_model.model.NoteEntity

@Database(
    entities = [NoteEntity::class],
    version = SCHEMA_VERSION,
    exportSchema = true
)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao

    companion object {
        const val SCHEMA_VERSION: Int = 1
    }
}