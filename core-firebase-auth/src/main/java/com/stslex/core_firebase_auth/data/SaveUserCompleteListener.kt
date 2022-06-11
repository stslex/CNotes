package com.stslex.core_firebase_auth.data

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.stslex.core.ValueState

@JvmInline
value class SaveUserCompleteListener(
    private val action: (ValueState<Unit>) -> Unit
) : OnCompleteListener<Void> {

    override fun onComplete(task: Task<Void>) {
        val result = if (task.isSuccessful) {
            ValueState.Success(Unit)
        } else {
            ValueState.Failure(task.exception!!)
        }
        action(result)
    }
}