package com.stslex.cnotes.data.model

interface NoteDataModel {
    fun id(): Int
    fun title(): String
    fun content(): String
    fun timestamp(): Long
}