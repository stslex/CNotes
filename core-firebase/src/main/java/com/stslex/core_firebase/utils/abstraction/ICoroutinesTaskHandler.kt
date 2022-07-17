package com.stslex.core_firebase.utils.abstraction

import com.google.android.gms.tasks.Task
import com.stslex.core.ValueState

fun interface ICoroutinesTaskHandler<T : Any> {
    suspend operator fun invoke(task: Task<T>): ValueState<T>
}