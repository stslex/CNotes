package com.stslex.core_model.model

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State

data class NoteUI(
    val id: Int,
    private val title: MutableState<String>,
    private val content: MutableState<String>,
    val timestamp: Long
) {
    fun setTitle(title: String) {
        this.title.value = title
    }

    fun getTitle(): State<String> = title

    fun setContent(content: String) {
        this.content.value = content
    }

    fun getContent(): State<String> = content
}