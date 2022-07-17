package com.stslex.core_firebase

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.stslex.core_firebase.utils.AppValueEventListener
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito

class AppValueEventListenerTest {

    @Test
    fun onCancelled() {
        var assertFlag = false
        val listener: ValueEventListener = AppValueEventListener(
            cancelled = {
                assertFlag = true
            },
            dataChange = {
                assertFlag = false
            }
        )
        listener.onCancelled(DatabaseError.fromException(Exception()))
        Assert.assertTrue(assertFlag)
    }

    @Test
    fun onDataChange() {
        var assertFlag = false
        val listener: ValueEventListener = AppValueEventListener(
            cancelled = {
                assertFlag = false
            },
            dataChange = {
                assertFlag = true
            }
        )
        listener.onDataChange(Mockito.mock(DataSnapshot::class.java))
        Assert.assertTrue(assertFlag)
    }

    @Test
    fun onDataChangeException() {
        var assertFlag = false
        val listener: ValueEventListener = AppValueEventListener(
            cancelled = {
                assertFlag = true
            },
            dataChange = {
                throw Exception()
            }
        )
        listener.onDataChange(Mockito.mock(DataSnapshot::class.java))
        Assert.assertTrue(assertFlag)
    }
}