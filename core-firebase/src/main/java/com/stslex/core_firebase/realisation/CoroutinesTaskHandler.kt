package com.stslex.core_firebase.realisation

import com.google.android.gms.tasks.Task
import com.stslex.core.ValueState
import com.stslex.core_firebase.abstraction.ICoroutinesTaskHandler
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CoroutinesTaskHandler<out T : Any>(
    private val task: Task<T>
) : ICoroutinesTaskHandler<T> {
    override suspend fun invoke(): ValueState<T> = suspendCoroutine { continuation ->
        task.addOnSuccessListener { continuation.resume(ValueState.Success(it)) }
        task.addOnFailureListener { continuation.resume(ValueState.Failure(it)) }
    }
}