package com.stslex.core_model.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class NoteDynamicUI(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: String,
    private val select: MutableState<Boolean> = mutableStateOf(false)
) {
    fun setSelect(select: Boolean) {
        this.select.value = select
    }

    fun isSelect(): State<Boolean> = select

    fun selectNotes(notes: SnapshotStateList<NoteDynamicUI>) {
        setSelect(!isSelect().value)
        if (notes.contains(this)) {
            notes.remove(this)
        } else notes.add(this)
    }
}