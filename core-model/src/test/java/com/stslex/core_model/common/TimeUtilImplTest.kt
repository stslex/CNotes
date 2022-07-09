package com.stslex.core_model.common

import org.junit.Assert
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class TimeUtilImplTest {

    @Test
    fun currentHour() {
        val timeUtil: TimeUtil = TimeUtilImpl()
        val time: Long = System.currentTimeMillis()
        val expectedValue = SimpleDateFormat("kk.mm", Locale.getDefault()).format(time)
        val actualValue = timeUtil.currentHour(time)
        Assert.assertEquals(expectedValue, actualValue)
    }
}