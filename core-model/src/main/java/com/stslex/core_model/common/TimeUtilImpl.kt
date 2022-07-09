package com.stslex.core_model.common

import java.text.SimpleDateFormat
import java.util.*

class TimeUtilImpl : TimeUtil {

    override fun currentHour(timestamp: Long): String =
        SimpleDateFormat(DATE_PATTERN, Locale.getDefault()).format(timestamp)

    companion object {
        private const val DATE_PATTERN = "kk.mm"
    }
}