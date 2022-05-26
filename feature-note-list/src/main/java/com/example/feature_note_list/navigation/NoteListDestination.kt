package com.example.feature_note_list.navigation

import com.stslex.core_navigation.AppNavigationDestination

object NoteListDestination : AppNavigationDestination {
    override val route: String
        get() = "note_list_route"
    override val destination: String
        get() = "note_list_destination"
}