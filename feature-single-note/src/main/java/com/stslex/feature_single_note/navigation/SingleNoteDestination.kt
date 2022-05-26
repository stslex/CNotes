package com.stslex.feature_single_note.navigation

import com.stslex.core_navigation.AppNavigationDestination

object SingleNoteDestination : AppNavigationDestination {
    override val route: String
        get() = "single_note_route"
    override val destination: String
        get() = "single_note_destination"
    const val noteIdArg: String = "noteId"
}