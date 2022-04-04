package com.stslex.cnotes.ui.model

import androidx.compose.runtime.State

interface NoteUIModel {
    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long
    fun setSelect(select: Boolean)
    fun isSelect(): State<Boolean>
}