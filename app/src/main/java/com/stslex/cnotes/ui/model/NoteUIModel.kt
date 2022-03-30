package com.stslex.cnotes.ui.model

interface NoteUIModel {
    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long
}