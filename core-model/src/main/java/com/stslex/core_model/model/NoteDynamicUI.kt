package com.stslex.core_model.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf

data class NoteDynamicUI(
    val id: Int,
    val title: String,
    val content: String,
    val timestamp: Long,
    val select: MutableState<Boolean> = mutableStateOf(false)
) {
    fun setSelect(select: Boolean) {
        this.select.value = select
    }

    fun isSelect(): State<Boolean> = select
}