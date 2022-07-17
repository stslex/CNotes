package com.stslex.feature_note_list

import com.stslex.feature_note_list.di.NoteListModule
import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class NoteListModuleTest : KoinTest {

    @After
    fun closeKoin() {
        stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules {
            NoteListModule().module
        }
    }
}