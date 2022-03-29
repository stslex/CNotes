package com.stslex.core

interface Mapper {

    fun interface Data<S, R> : Mapper {
        fun map(data: S): R
    }

    interface ToDomain<S, R> : Data<S, R> {
        fun map(exception: Exception): R
    }

    interface ToUI<S, R> : ToDomain<S, R> {
        fun map(): R
    }
}