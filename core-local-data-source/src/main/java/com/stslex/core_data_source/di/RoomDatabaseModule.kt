package com.stslex.core_data_source.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.stslex.core_data_source.database.NoteRoomDatabase
import com.stslex.core_data_source.database.NoteRoomDatabaseCallback
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class RoomDatabaseModule {

    val module = module {

        single {
            get<NoteRoomDatabase>().noteDao()
        }

        single {
            Room.databaseBuilder(
                androidContext(),
                NoteRoomDatabase::class.java,
                "note_database"
            ).addCallback(get()).build()
        }

        single<RoomDatabase.Callback> {
            NoteRoomDatabaseCallback(
                scope = CoroutineScope(SupervisorJob()),
                context = androidContext(),
                dispatchers = get()
            )
        }
    }
}