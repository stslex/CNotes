package com.stslex.cnotes

import android.app.Application
import com.stslex.feature_auth_code.di.AuthCodeModule
import com.stslex.feature_auth_phonenumber.di.AuthPhoneNumberModule
import com.stslex.feature_note_list.di.NoteListModule
import com.stslex.core.CoroutinesModule
import com.stslex.core_data_source.di.RoomDatabaseModule
import com.stslex.core_firebase.di.FirebaseModule
import com.stslex.core_model.di.MapperModule
import com.stslex.feature_profile.di.ProfileModule
import com.stslex.feature_single_note.di.SingleNoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CNoteApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@CNoteApplication)
            modules(
                AppComponent().appModules,
                RoomDatabaseModule().module,
                CoroutinesModule().module,
                NoteListModule().module,
                FirebaseModule().module,
                AuthPhoneNumberModule().module,
                AuthCodeModule().module,
                ProfileModule().module,
                MapperModule().module,
                SingleNoteModule().module
            )
        }
        super.onCreate()
    }
}
