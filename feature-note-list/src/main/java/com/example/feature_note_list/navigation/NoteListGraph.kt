package com.example.feature_note_list.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.List
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.feature_note_list.R
import com.example.feature_note_list.ui.NoteListRoute
import com.stslex.core_navigation.TopLevelDestination

fun NavGraphBuilder.noteListGraph(
    openSingleNote: (Int) -> Unit,
    openProfile: () -> Unit,
    openAuthPhoneNumber: () -> Unit,
    nestedGraphs: NavGraphBuilder.() -> Unit
) {
    navigation(
        route = NoteListDestination.route,
        startDestination = NoteListDestination.destination
    ) {
        composable(route = NoteListDestination.destination) {
            NoteListRoute(
                openSingleNote = openSingleNote,
                openProfile = openProfile,
                openAuthPhoneNumber = openAuthPhoneNumber
            )
        }
        nestedGraphs()
    }
}

val noteListTopLevelDestination = TopLevelDestination(
    route = NoteListDestination.route,
    selectedIcon = Icons.Filled.List,
    unselectedIcon = Icons.Outlined.List,
    iconTextId = R.string.all_notes_label
)