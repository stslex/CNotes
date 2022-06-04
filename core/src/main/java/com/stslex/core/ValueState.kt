package com.stslex.core

sealed class ValueState<out T> : ValueStructure<T> {

    data class Success<T>(val data: T) : ValueState<T>() {
        override fun <R> map(mapper: Mapper.ToUI<T, R>): R = mapper.map(data)
        override fun <U, R> transform(
            transformer: Transformer.ToUI<T, U, R>,
            secondData: U
        ): R = transformer.map(data, secondData)
    }

    data class Failure(val exception: Exception) : ValueState<Nothing>() {
        override fun <R> map(mapper: Mapper.ToUI<Nothing, R>): R = mapper.map(exception)
        override fun <U, R> transform(
            transformer: Transformer.ToUI<Nothing, U, R>,
            secondData: U
        ): R = transformer.map(exception)
    }

    object Loading : ValueState<Nothing>() {
        override fun <R> map(mapper: Mapper.ToUI<Nothing, R>): R = mapper.map()
        override fun <U, R> transform(
            transformer: Transformer.ToUI<Nothing, U, R>,
            secondData: U
        ): R = transformer.map()
    }
}