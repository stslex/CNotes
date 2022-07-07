package com.stslex.core_firebase

import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class FirebaseModuleTest : KoinTest {

    @After
    fun closeKoin() {
        stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules {
            FirebaseModule().module
        }
    }
}