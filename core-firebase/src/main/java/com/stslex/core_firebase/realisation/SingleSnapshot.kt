package com.stslex.core_firebase.realisation

import com.google.firebase.database.ValueEventListener
import com.stslex.core.ValueState
import com.stslex.core_firebase.AppValueEventListener
import com.stslex.core_firebase.abstraction.AppSnapshotListener
import kotlin.reflect.KClass

@JvmInline
value class SingleSnapshot<in T : Any>(
    private val event: (ValueState<T>) -> Unit
) : AppSnapshotListener<T> {

    override fun invoke(type: KClass<out T>): ValueEventListener = AppValueEventListener(
        cancelled = event::invoke,
        dataChange = { snapshot ->
            val result = checkNotNull(snapshot.getValue(type.java))
            event(ValueState.Success(result))
        }
    )
}