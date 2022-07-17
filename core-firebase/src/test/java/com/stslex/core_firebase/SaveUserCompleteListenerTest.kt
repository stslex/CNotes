package com.stslex.core_firebase

import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Tasks
import com.stslex.core.ValueState
import com.stslex.core_firebase.utils.SaveUserCompleteListener
import org.junit.Assert
import org.junit.Test

class SaveUserCompleteListenerTest {

    @Test
    fun onCompleteException() {
        var resultFlag = false
        val exceptedException = Exception()
        val listener: OnCompleteListener<Void> = SaveUserCompleteListener {
            resultFlag = when (it) {
                is ValueState.Success -> false
                is ValueState.Failure -> exceptedException == it.exception
                is ValueState.Loading -> false
            }
        }
        listener.onComplete(Tasks.forException(exceptedException))
        Assert.assertTrue(resultFlag)
    }

    @Test
    fun onCompleteCanceled() {
        var resultFlag = false
        val listener: OnCompleteListener<Void> = SaveUserCompleteListener {
            resultFlag = when (it) {
                is ValueState.Success -> false
                is ValueState.Failure -> "Cancelled" == it.exception.message
                is ValueState.Loading -> false
            }
        }
        listener.onComplete(Tasks.forCanceled())
        Assert.assertTrue(resultFlag)
    }

    @Test
    fun onCompleteSuccess() {
        var resultFlag = false
        val listener: OnCompleteListener<Void> = SaveUserCompleteListener {
            resultFlag = when (it) {
                is ValueState.Success -> true
                is ValueState.Failure -> false
                is ValueState.Loading -> false
            }
        }
        listener.onComplete(Tasks.forResult(null))
        Assert.assertTrue(resultFlag)
    }
}