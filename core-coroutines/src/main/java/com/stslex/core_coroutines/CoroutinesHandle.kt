package com.stslex.core_coroutines

import com.google.android.gms.tasks.Task
import com.stslex.core.ValueState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface CoroutinesHandler {

    suspend fun <T> handleTask(task: Task<T>): ValueState<T>

    class Base : CoroutinesHandler {

        override suspend fun <T> handleTask(task: Task<T>): ValueState<T> =
            suspendCoroutine { continuation ->
                task.addOnSuccessListener { continuation.resume(ValueState.Success(it)) }
                task.addOnFailureListener { continuation.resume(ValueState.Failure(it)) }
            }
    }
}