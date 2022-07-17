package com.stslex.core_firebase.utils.abstraction

import com.google.firebase.database.ValueEventListener
import kotlin.reflect.KClass

fun interface AppSnapshotListener<in T : Any> {
    operator fun invoke(type: KClass<out T>): ValueEventListener
}