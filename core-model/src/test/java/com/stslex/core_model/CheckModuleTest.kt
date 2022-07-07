package com.stslex.core_model

import com.stslex.core_model.di.MapperModule
import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class MapperModuleTest : KoinTest {

    @After
    fun closeKoin() {
        stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules {
            MapperModule().module
        }
    }
}