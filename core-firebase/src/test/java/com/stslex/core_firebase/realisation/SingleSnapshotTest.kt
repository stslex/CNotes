package com.stslex.core_firebase.realisation

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.stslex.core.ValueState
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class SingleSnapshotTest {

    @Test
    fun invokeFailure() {
        var assertFlag = false
        val listener: ValueEventListener = SingleSnapshot<String> {
            assertFlag = when (it) {
                is ValueState.Success -> false
                is ValueState.Failure -> true
                is ValueState.Loading -> false
            }
        }.invoke(String::class)
        Assert.assertNotNull(listener)
        listener.onCancelled(DatabaseError.fromException(Exception()))
        Assert.assertTrue(assertFlag)
    }

    @Test
    fun invokeSuccess() {
        val expectedResult = "result"
        var assertFlag = false
        val listener: ValueEventListener = SingleSnapshot<String> {
            assertFlag = when (it) {
                is ValueState.Success -> expectedResult == it.data
                is ValueState.Failure -> false
                is ValueState.Loading -> false
            }
        }.invoke(String::class)
        Assert.assertNotNull(listener)
        val snapshot = Mockito.spy(Mockito.mock(DataSnapshot::class.java))
        Mockito.doReturn(expectedResult).`when`(snapshot).getValue(String::class.java)
        listener.onDataChange(snapshot)
        Assert.assertTrue(assertFlag)
    }
}