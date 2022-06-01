package com.stslex.feature_profile.data

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlin.reflect.KClass

class ListValueEventListener<T : Any>(
    private val type: KClass<T>,
    private val eventResult: (List<T>) -> Unit
) : ValueEventListener {

    override fun onDataChange(snapshot: DataSnapshot) {
        val result: List<T> = snapshot.children.mapNotNull {
            it.getValue(type.java)
        }
        eventResult(result)
    }

    override fun onCancelled(error: DatabaseError) {
        //Not implemented yet
    }
}