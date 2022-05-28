package com.stslex.core_data_source

import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        get<NoteRoomDatabase>().noteDao()
    }

    single {
        NoteRoomDatabase.getDatabase(androidContext())
    }

    single<RoomDatabase.Callback> {
        NoteRoomDatabaseCallback(CoroutineScope(SupervisorJob()), get())
    }
}