package com.stslex.core_firebase.utils.realisation

import com.google.android.gms.tasks.Task
import com.stslex.core.ValueState
import com.stslex.core_firebase.utils.abstraction.ICoroutinesTaskHandler
import java.util.concurrent.Executor
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class CoroutinesTaskHandler<T : Any>(
    private val executor: Executor
) : ICoroutinesTaskHandler<T> {
    override suspend operator fun invoke(task: Task<T>): ValueState<T> =
        suspendCoroutine { continuation ->
            task.addOnSuccessListener(executor) { continuation.resume(ValueState.Success(it)) }
            task.addOnFailureListener(executor) { continuation.resume(ValueState.Failure(it)) }
        }
}