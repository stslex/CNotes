package com.stslex.cnotes

import android.app.Application
import com.example.feature_auth_code.di.authCodeModule
import com.example.feature_auth_phonenumber.di.authPhoneNumberModule
import com.example.feature_note_list.di.noteListModule
import com.stslex.core_coroutines.coroutinesModule
import com.stslex.core_data_source.roomDatabaseModule
import com.stslex.core_firebase.firebaseModule
import com.stslex.core_firebase_auth.di.firebaseAuthModule
import com.stslex.core_model.di.mapperModule
import com.stslex.feature_profile.di.profileModule
import com.stslex.feature_single_note.di.singleNoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class CNoteApplication : Application() {

    override fun onCreate() {
        startKoin {
            androidLogger()
            androidContext(this@CNoteApplication)
            modules(
                roomDatabaseModule,
                coroutinesModule,
                mapperModule,
                singleNoteModule,
                noteListModule,
                firebaseModule,
                authPhoneNumberModule,
                authCodeModule,
                profileModule,
                firebaseAuthModule
            )
        }
        super.onCreate()
    }
}
