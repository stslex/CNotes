package com.stslex.cnotes

import com.example.feature_auth_code.di.AuthCodeModule
import com.example.feature_auth_phonenumber.di.AuthPhoneNumberModule
import com.example.feature_note_list.di.NoteListModule
import com.stslex.cnotes.di.AppComponent
import com.stslex.core_coroutines.CoroutinesModule
import com.stslex.core_data_source.di.RoomDatabaseModule
import com.stslex.core_firebase.FirebaseModule
import com.stslex.core_firebase_auth.di.FirebaseAuthModule
import com.stslex.core_model.di.MapperModule
import com.stslex.core_remote_data_source.di.RemoteDataSourceModule
import com.stslex.feature_profile.di.ProfileModule
import com.stslex.feature_single_note.di.SingleNoteModule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.koin.test.mock.MockProviderRule
import org.mockito.Mockito
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class CheckModulesTest : KoinTest {

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Test
    fun checkAllModules() = startKoin {
        androidContext(RuntimeEnvironment.getApplication())
    }.checkModules {
        AppComponent().appModules
        RoomDatabaseModule().module
        CoroutinesModule().module
        NoteListModule().module
        FirebaseModule().module
        AuthPhoneNumberModule().module
        AuthCodeModule().module
        ProfileModule().module
        RemoteDataSourceModule().module
        MapperModule().module
        FirebaseAuthModule().module
        SingleNoteModule().module
    }
}