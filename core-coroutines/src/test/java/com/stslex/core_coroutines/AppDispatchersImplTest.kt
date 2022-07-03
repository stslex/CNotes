package com.stslex.core_coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.junit.Assert
import org.junit.Test

class AppDispatchersImplTest {

    @Test
    fun getIo() {
        val dispatcher: CoroutineDispatcher = appDispatchers.io
        Assert.assertEquals(Dispatchers.IO, dispatcher)
    }

    @Test
    fun getDefault() {
        val dispatcher: CoroutineDispatcher = appDispatchers.default
        Assert.assertEquals(Dispatchers.Default, dispatcher)
    }

    @Test
    fun getUnconfined() {
        val dispatcher: CoroutineDispatcher = appDispatchers.unconfined
        Assert.assertEquals(Dispatchers.Unconfined, dispatcher)
    }

    @Test
    fun getMain() {
        val dispatcher: CoroutineDispatcher = appDispatchers.main
        Assert.assertEquals(Dispatchers.Main, dispatcher)
    }

    companion object {

        @JvmStatic
        private val appDispatchers: AppDispatchers = AppDispatchersImpl()
    }
}