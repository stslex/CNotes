package com.stslex.core_data_source

import com.stslex.core_data_source.di.RoomDatabaseModule
import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class RoomDatabaseModuleTest : KoinTest {

    @After
    fun closeKoin() {
        stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules {
            RoomDatabaseModule().module
        }
    }
}