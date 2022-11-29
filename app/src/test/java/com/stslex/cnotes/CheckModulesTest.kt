package com.stslex.cnotes

import com.stslex.feature_auth_code.di.AuthCodeModule
import com.stslex.feature_auth_phonenumber.di.AuthPhoneNumberModule
import com.stslex.feature_note_list.di.NoteListModule
import com.stslex.core.CoroutinesModule
import com.stslex.core_data_source.di.RoomDatabaseModule
import com.stslex.core_firebase.di.FirebaseModule
import com.stslex.core_model.di.MapperModule
import com.stslex.feature_profile.di.ProfileModule
import com.stslex.feature_single_note.di.SingleNoteModule
import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class CheckModulesTest : KoinTest {

    @After
    fun closeKoin() {
        stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules {
            AppComponent().appModules
            RoomDatabaseModule().module
            CoroutinesModule().module
            NoteListModule().module
            FirebaseModule().module
            AuthPhoneNumberModule().module
            AuthCodeModule().module
            ProfileModule().module
            MapperModule().module
            SingleNoteModule().module
        }
    }
}