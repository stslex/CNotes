package com.stslex.core_firebase.utils

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.stslex.core.ValueState

class AppValueEventListener(
    val cancelled: (ValueState.Failure) -> Unit,
    val dataChange: (snapshot: DataSnapshot) -> Unit
) : ValueEventListener {

    override fun onCancelled(error: DatabaseError) =
        cancelled(ValueState.Failure(error.toException()))

    override fun onDataChange(snapshot: DataSnapshot) = try {
        dataChange(snapshot)
    } catch (exception: Exception) {
        cancelled(ValueState.Failure(exception))
    }
}