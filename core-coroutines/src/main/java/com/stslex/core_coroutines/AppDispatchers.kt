package com.stslex.core_coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainCoroutineDispatcher

interface AppDispatchers {

    val main: MainCoroutineDispatcher
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val unconfined: CoroutineDispatcher

    class Base(
        override val main: MainCoroutineDispatcher = Dispatchers.Main,
        override val io: CoroutineDispatcher = Dispatchers.IO,
        override val default: CoroutineDispatcher = Dispatchers.Default,
        override val unconfined: CoroutineDispatcher = Dispatchers.Unconfined
    ) : AppDispatchers
}