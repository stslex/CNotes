package com.stslex.core_coroutines

import com.stslex.core.ValueState

fun interface ICoroutinesTaskHandler<out T : Any> {
    suspend operator fun invoke(): ValueState<T>
}