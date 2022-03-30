package com.stslex.cnotes.ui.model

data class NoteUIModelImpl(
    private val id: Int = 0,
    private val title: String,
    private val content: String,
    private val timestamp: Long
) : NoteUIModel {

    override fun id(): Int = id

    override fun title(): String = title

    override fun content(): String = content

    override fun timestamp(): Long = timestamp
}