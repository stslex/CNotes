package com.stslex.core_model.data

interface NoteDataModel {

    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long

    data class Base(
        private val id: Int = 0,
        private val title: String = "",
        private val content: String = "",
        private val timestamp: Long = System.currentTimeMillis()
    ) : NoteDataModel {

        override fun id(): Int = id

        override fun title(): String = title

        override fun content(): String = content

        override fun timestamp(): Long = timestamp
    }
}