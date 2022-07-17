package com.stslex.core

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.Assert.assertEquals
import org.junit.Test

class AppDispatchersImplTest {

    @Test
    fun getIo() {
        val dispatcher: CoroutineDispatcher = appDispatchers.io
        assertEquals(Dispatchers.IO, dispatcher)
    }

    @Test
    fun getDefault() {
        val dispatcher: CoroutineDispatcher = appDispatchers.default
        assertEquals(Dispatchers.Default, dispatcher)
    }

    @Test
    fun getUnconfined() {
        val dispatcher: CoroutineDispatcher = appDispatchers.unconfined
        assertEquals(Dispatchers.Unconfined, dispatcher)
    }

    @Test
    fun getMain() {
        val dispatcher: CoroutineDispatcher = appDispatchers.main
        assertEquals(Dispatchers.Main, dispatcher)
    }

    companion object {

        @JvmStatic
        private val appDispatchers: AppDispatchers = AppDispatchersImpl()
    }
}