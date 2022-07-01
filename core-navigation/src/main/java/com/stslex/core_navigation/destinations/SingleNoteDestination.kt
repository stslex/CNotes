package com.stslex.core_navigation.destinations

object SingleNoteDestination : AppDestinations {
    override val route: String
        get() = "single_note_route"
    override val destination: String
        get() = "single_note_destination"
    const val noteIdArg: String = "noteId"
}