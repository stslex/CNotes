package com.stslex.core

interface Mapper {

    fun interface Data<in S, out R> : Mapper {
        fun map(data: S): R
    }

    interface ToUI<in S, out R> : Data<S, R> {
        fun map(exception: Exception): R
        fun map(): R
    }
}