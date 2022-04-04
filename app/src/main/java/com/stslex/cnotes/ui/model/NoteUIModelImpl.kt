package com.stslex.cnotes.ui.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class NoteUIModelImpl(
    private val id: Int = 0,
    private val title: String,
    private val content: String,
    private val timestamp: Long,
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
}