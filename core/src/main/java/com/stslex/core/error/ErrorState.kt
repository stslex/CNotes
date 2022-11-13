package com.stslex.core.error

sealed class ErrorState {
    object Default : ErrorState()
    data class Error(val throwable: Throwable) : ErrorState()
}
