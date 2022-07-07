package com.stslex.core_firebase.realisation

import com.google.android.gms.tasks.Tasks
import com.stslex.core.ValueState
import com.stslex.core_firebase.abstraction.ICoroutinesTaskHandler
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class CoroutinesTaskHandlerTest {

    @Test
    fun invokeSuccess() = runBlocking {
        val expectedResult = "result"
        val task = Tasks.forResult(expectedResult)
        val handler: ICoroutinesTaskHandler<String> = CoroutinesTaskHandler(task)
        when (val actual = handler.invoke()) {
            is ValueState.Success -> Assert.assertEquals(expectedResult, actual.data)
            is ValueState.Loading -> Assert.fail()
            is ValueState.Failure -> Assert.fail()
        }
    }

    @Test
    fun invokeFailure() = runBlocking {
        val expectedException = Exception()
        val task = Tasks.forException<String>(expectedException)
        val handler: ICoroutinesTaskHandler<String> = CoroutinesTaskHandler(task)
        when (val actual = handler.invoke()) {
            is ValueState.Success -> Assert.fail()
            is ValueState.Loading -> Assert.fail()
            is ValueState.Failure -> Assert.assertEquals(expectedException, actual.exception)
        }
    }
}