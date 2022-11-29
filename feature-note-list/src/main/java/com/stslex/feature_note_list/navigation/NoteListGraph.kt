package com.stslex.feature_note_list.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.core_navigation.destinations.NoteListDestination
import com.stslex.feature_note_list.R
import com.stslex.feature_note_list.ui.NotesScreen

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.noteListGraph(
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
            NotesScreen(
                modifier = Modifier.windowInsetsPadding(WindowInsets.safeDrawing)
            )
        }
        nestedGraphs()
    }
}

val noteListTopLevelDestination = TopLevelDestination(
    destination = NoteListDestination.destination,
    route = NoteListDestination.route,
    selectedIcon = Icons.Filled.List,
    unselectedIcon = Icons.Outlined.List,
    iconTextId = R.string.all_notes_label
)