package com.stslex.core_data_source

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val roomDatabaseModule = module {
    single {
        NoteRoomDatabase.getDatabase(androidContext(), CoroutineScope(SupervisorJob())).noteDao()
    }
}