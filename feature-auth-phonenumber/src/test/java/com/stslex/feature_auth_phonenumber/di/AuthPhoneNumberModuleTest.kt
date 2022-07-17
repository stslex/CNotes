package com.stslex.feature_auth_phonenumber.di

import org.junit.After
import org.junit.Test
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class AuthPhoneNumberModuleTest : KoinTest {

    @After
    fun closeKoin() {
        stopKoin()
    }

    @Test
    fun checkAllModules() {
        checkModules {
            AuthPhoneNumberModule().module
        }
    }
}