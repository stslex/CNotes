package com.stslex.core_firebase.realisation

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.stslex.core.ValueState
import com.stslex.core_firebase.utils.realisation.ListSnapshot
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.util.*

class ListSnapshotTest {

    @Test
    fun invokeFailure() {
        var assertFlag = false
        val listener: ValueEventListener = ListSnapshot<String> {
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
        val list = Collections.nCopies(10, Mockito.spy(Mockito.mock(DataSnapshot::class.java)))
        list.forEachIndexed { index, dataSnapshot ->
            Mockito.doReturn(index).`when`(dataSnapshot).getValue(String::class.java)
        }
        var assertFlag = false
        val listener: ValueEventListener = ListSnapshot<String> {
            assertFlag = when (it) {
                is ValueState.Success -> true
                is ValueState.Failure -> false
                is ValueState.Loading -> false
            }
        }.invoke(String::class)
        Assert.assertNotNull(listener)
        val snapshot = Mockito.spy(Mockito.mock(DataSnapshot::class.java))
        val snapshots: Iterable<DataSnapshot> = object : Iterable<DataSnapshot> {
            override fun iterator(): Iterator<DataSnapshot> = list.iterator()
        }
        Mockito.doReturn(snapshots).`when`(snapshot).children
        listener.onDataChange(snapshot)
        Assert.assertTrue(assertFlag)
    }
}