package com.stslex.core_model.common

import com.stslex.core.ValueState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.mapNotNull
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface PrimaryMapper {

    fun <T> map(process: () -> Flow<T>): Flow<ValueState<T>>

    suspend fun <T> map(process: () -> T): ValueState<T>

    class Base : PrimaryMapper {

        override fun <T> map(process: () -> Flow<T>): Flow<ValueState<T>> = try {
            process().mapNotNull { ValueState.Success(it) }
        } catch (exception: IOException) {
            flowOf(ValueState.Failure(exception))
        }

        override suspend fun <T> map(process: () -> T): ValueState<T> =
            suspendCoroutine { continuation ->
                try {
                    val result = process()
                    continuation.resume(ValueState.Success(result))
                } catch (exception: IOException) {
                    continuation.resume(ValueState.Failure(exception))
                }
            }
    }
}