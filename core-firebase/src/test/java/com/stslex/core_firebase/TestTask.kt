package com.stslex.core_firebase

import android.app.Activity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import java.util.concurrent.Executor

class TestTask(
    private val isSuccessful: Boolean
) : Task<DataSnapshot>() {

    override fun addOnFailureListener(p0: OnFailureListener): Task<DataSnapshot> {
        TODO("Not yet implemented")
    }

    override fun addOnFailureListener(p0: Activity, p1: OnFailureListener): Task<DataSnapshot> {
        TODO("Not yet implemented")
    }

    override fun addOnFailureListener(p0: Executor, p1: OnFailureListener): Task<DataSnapshot> {
        TODO("Not yet implemented")
    }

    override fun addOnSuccessListener(p0: OnSuccessListener<in DataSnapshot>): Task<DataSnapshot> {
        TODO("Not yet implemented")
    }

    override fun addOnSuccessListener(
        p0: Activity,
        p1: OnSuccessListener<in DataSnapshot>
    ): Task<DataSnapshot> {
        TODO("Not yet implemented")
    }

    override fun addOnCompleteListener(p0: OnCompleteListener<DataSnapshot>): Task<DataSnapshot> {
        return super.addOnCompleteListener(p0)
    }

    override fun addOnSuccessListener(
        p0: Executor,
        p1: OnSuccessListener<in DataSnapshot>
    ): Task<DataSnapshot> {
        TODO("Not yet implemented")
    }

    override fun getException(): Exception? {
        TODO("Not yet implemented")
    }

    override fun getResult(): DataSnapshot {
        TODO("Not yet implemented")
    }

    override fun <X : Throwable?> getResult(p0: Class<X>): DataSnapshot {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean = !isSuccessful

    override fun isComplete(): Boolean = isSuccessful

    override fun isSuccessful(): Boolean = isSuccessful
}