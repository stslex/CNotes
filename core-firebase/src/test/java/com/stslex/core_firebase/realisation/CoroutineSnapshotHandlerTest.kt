package com.stslex.core_firebase.realisation

import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.firebase.database.DataSnapshot
import com.stslex.core.ValueState
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class CoroutineSnapshotHandlerTest {

    private val snapshotHandler = CoroutineSnapshotHandler<String>()

    @Test
    fun invoke(): Unit = runBlocking {
        val list = listOf(Mockito.spy(Mockito.mock(DataSnapshot::class.java)))
        list.forEachIndexed { index, dataSnapshot ->
            Mockito.doReturn(index).`when`(dataSnapshot).getValue(String::class.java)
        }
        val dataSnapshot: DataSnapshot = Mockito.spy(Mockito.mock(DataSnapshot::class.java))
        val snapshots: Iterable<DataSnapshot> = object : Iterable<DataSnapshot> {
            override fun iterator(): Iterator<DataSnapshot> = list.iterator()
        }
        Mockito.doReturn(snapshots).`when`(dataSnapshot).children
        val task: Task<DataSnapshot> = Tasks.forResult(dataSnapshot)
        when (val result =
            snapshotHandler.invoke(task, String::class)) {
            is ValueState.Success -> {
                Assert.assertTrue(result.data.isNotEmpty())
            }
            is ValueState.Failure -> Assert.fail(result.exception.message)
            is ValueState.Loading -> Assert.fail()
        }
    }

    @Test
    fun invokeException(): Unit = runBlocking {
        val exception = Exception("test exception")
        val task: Task<DataSnapshot> = Tasks.forException(exception)
        when (val result = snapshotHandler.invoke(task, String::class)) {
            is ValueState.Success -> Assert.fail()
            is ValueState.Failure -> Assert.assertEquals(exception, result.exception)
            is ValueState.Loading -> Assert.fail()
        }
    }

    @Test
    fun invokeCancelled(): Unit = runBlocking {
        val task: Task<DataSnapshot> = Tasks.forCanceled()
        when (snapshotHandler.invoke(task, String::class)) {
            is ValueState.Success -> Assert.fail()
            is ValueState.Failure -> {
                Assert.assertTrue(true)
            }
            is ValueState.Loading -> Assert.fail()
        }
    }
}