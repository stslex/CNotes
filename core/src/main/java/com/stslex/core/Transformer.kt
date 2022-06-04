package com.stslex.core

interface Transformer {

    fun interface Data<in S, in U, out R> : Mapper {
        fun map(firstData: S, secondData: U): R
    }

    interface ToUI<in S, in U, out R> : Data<S, U, R> {
        fun map(exception: Exception): R
        fun map(): R
    }
}