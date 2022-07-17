package com.stslex.feature_note_list.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.List
import androidx.navigation.NavGraphBuilder
import com.stslex.feature_note_list.R
import com.stslex.feature_note_list.ui.NoteListRoute
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stslex.core_navigation.destinations.NoteListDestination
import com.stslex.core_navigation.TopLevelDestination

@OptIn(ExperimentalAnimationApi::class)
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
        composable(
            route = NoteListDestination.destination,
            enterTransition = NoteListTransitions.enterTransition,
            exitTransition = NoteListTransitions.exitTransition,
            popEnterTransition = NoteListTransitions.popEnterTransition,
            popExitTransition = NoteListTransitions.popExitTransition
        ) {
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