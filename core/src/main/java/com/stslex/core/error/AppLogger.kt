package com.stslex.core.error

import android.util.Log

object AppLogger {

    private const val APP_EXCEPTION = "APP_EXCEPTION"
    private const val APP_EXCEPTION_SEPARATOR = "::"

    fun getExceptionTag(vararg strings: String): String = strings
        .filter { it.isNotBlank() }
        .joinToString(separator = APP_EXCEPTION_SEPARATOR)

    fun logException(throwable: Throwable, destination: String = "") {
        val tag = getExceptionTag(APP_EXCEPTION, destination)
        Log.e(tag, throwable.message, throwable)
    }
}