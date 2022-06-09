package com.stslex.core_firebase.realisation

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.stslex.core.ValueState
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass

class CoroutineSnapshotHandler<T : Any> {

    suspend operator fun invoke(
        task: Task<DataSnapshot>,
        type: KClass<out T>
    ): ValueState<List<T>> = suspendCoroutine { continuation ->
        task.addOnCompleteListener { taskResult ->
            if (taskResult.isSuccessful) {
                val result: List<T> = taskResult.result.children.mapNotNull {
                    it.getValue(type.java)
                }
                continuation.resume(ValueState.Success(result))
            } else {
                continuation.resume(ValueState.Failure(taskResult.exception!!))
            }
        }
    }
}