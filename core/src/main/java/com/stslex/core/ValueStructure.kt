package com.stslex.core

interface ValueStructure<out T> {
    fun <R> map(mapper: Mapper.ToUI<T, R>): R
    fun <U, R> transform(transformer: Transformer.ToUI<T, U, R>, secondData: U): R
}