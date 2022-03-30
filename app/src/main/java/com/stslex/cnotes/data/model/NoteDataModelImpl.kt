package com.stslex.cnotes.data.model

data class NoteDataModelImpl(
    private val id: Int = 0,
    private val title: String,
    private val content: String,
    private val timestamp: Long
) : NoteDataModel {

    override fun id(): Int = id

    override fun title(): String = title

    override fun content(): String = content

    override fun timestamp(): Long = timestamp
}