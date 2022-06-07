package com.stslex.core

sealed interface ValueState<out T> {

    fun <R> map(mapper: Mapper.ToUI<T, R>): R
    fun <U, R> transform(transformer: Transformer.ToUI<T, U, R>, secondData: U): R

    data class Success<T>(val data: T) : ValueState<T> {
        override fun <R> map(mapper: Mapper.ToUI<T, R>): R = mapper.map(data)
        override fun <U, R> transform(
            transformer: Transformer.ToUI<T, U, R>,
            secondData: U
        ): R = transformer.transform(data, secondData)
    }

    data class Failure(val exception: Exception) : ValueState<Nothing> {
        override fun <R> map(mapper: Mapper.ToUI<Nothing, R>): R = mapper.map(exception)
        override fun <U, R> transform(
            transformer: Transformer.ToUI<Nothing, U, R>,
            secondData: U
        ): R = transformer.transform(exception)
    }

    object Loading : ValueState<Nothing> {
        override fun <R> map(mapper: Mapper.ToUI<Nothing, R>): R = mapper.map()
        override fun <U, R> transform(
            transformer: Transformer.ToUI<Nothing, U, R>,
            secondData: U
        ): R = transformer.transform()
    }
}