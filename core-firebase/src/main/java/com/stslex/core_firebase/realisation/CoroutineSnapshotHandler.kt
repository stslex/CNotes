package com.stslex.core_firebase.realisation

import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.stslex.core.ValueState
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import kotlin.reflect.KClass

class CoroutineSnapshotHandler<T : Any> {

    suspend operator fun invoke(
        task: Task<DataSnapshot>,
        type: KClass<out T>,
        executors: Executor = Executors.newSingleThreadExecutor()
    ): ValueState<List<T>> = suspendCoroutine { continuation ->
        try {
            task.addOnCompleteListener(executors) { taskResult ->
                if (taskResult.isSuccessful) {
                    val result: List<T> = taskResult.result.children.mapNotNull {
                        it.getValue(type.java)
                    }
                    continuation.resume(ValueState.Success(result))
                } else {
                    val exception = taskResult.exception ?: Exception(NO_EXCEPTIONS)
                    continuation.resume(ValueState.Failure(exception))
                }
            }
        } catch (exception: Exception) {
            continuation.resume(ValueState.Failure(exception))
        }
    }

    companion object {
        private const val NO_EXCEPTIONS = "No exception"
    }
}