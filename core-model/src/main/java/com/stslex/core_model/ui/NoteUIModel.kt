package com.stslex.core_model.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

interface NoteUIModel {

    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long
    fun setSelect(select: Boolean)
    fun isSelect(): State<Boolean>
    fun setTitle(title: String)
    fun setContent(content: String)
    fun setTimeStamp(timestamp: Long)

    data class Base(
        private val id: Int = 0,
        private var title: String = "",
        private var content: String = "",
        private var timestamp: Long = System.currentTimeMillis(),
        private var select: MutableState<Boolean> = mutableStateOf(false)
    ) : NoteUIModel {

        override fun id(): Int = id

        override fun title(): String = title

        override fun content(): String = content

        override fun timestamp(): Long = timestamp

        override fun setSelect(select: Boolean) {
            this.select.value = select
        }

        override fun isSelect(): State<Boolean> = select

        override fun setTitle(title: String) {
            this.title = title
        }

        override fun setContent(content: String) {
            this.content = content
        }

        override fun setTimeStamp(timestamp: Long) {
            this.timestamp = timestamp
        }
    }
}