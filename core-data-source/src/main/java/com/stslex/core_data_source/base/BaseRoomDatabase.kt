package com.stslex.core_data_source.base

interface BaseRoomDatabase<T> {
    fun dao(): T
}