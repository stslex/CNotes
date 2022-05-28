package com.stslex.core_data_source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.stslex.core_data_source.NoteRoomDatabase.Companion.SCHEMA_VERSION
import com.stslex.core_model.model.NoteEntity
import org.koin.java.KoinJavaComponent.inject

@Database(
    entities = [NoteEntity::class],
    version = SCHEMA_VERSION,
    exportSchema = false
)
abstract class NoteRoomDatabase : RoomDatabase() {

    abstract fun noteDao(): NoteDao


    companion object {

        const val SCHEMA_VERSION: Int = 1

        @Volatile
        private var INSTANCE: NoteRoomDatabase? = null

        private val databaseCallback: Callback by inject(Callback::class.java)

        fun getDatabase(
            context: Context
        ): NoteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteRoomDatabase::class.java,
                    "note_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(databaseCallback)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}