package com.stslex.cnotes

import android.app.Application
import com.example.feature_note_list.di.noteListModule
import com.stslex.core_coroutines.coroutinesModule
import com.stslex.core_data_source.roomDatabaseModule
import com.stslex.core_model.di.mapperModule
import com.stslex.feature_single_note.di.singleNoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CNoteApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@CNoteApplication)
            modules(
                roomDatabaseModule,
                coroutinesModule,
                mapperModule,
                singleNoteModule,
                noteListModule
            )
        }
    }
}
