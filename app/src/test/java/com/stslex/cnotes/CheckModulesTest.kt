package com.stslex.cnotes

import com.example.feature_note_list.di.noteListModule
import com.stslex.core_coroutines.coroutinesModule
import com.stslex.core_data_source.roomDatabaseModule
import com.stslex.core_model.di.mapperModule
import com.stslex.feature_single_note.di.singleNoteModule
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
        roomDatabaseModule
        coroutinesModule
        mapperModule
        singleNoteModule
        noteListModule
    }
}