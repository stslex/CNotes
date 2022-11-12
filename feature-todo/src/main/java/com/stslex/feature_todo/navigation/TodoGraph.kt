package com.stslex.feature_todo.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.core_navigation.destinations.NoteListDestination
import com.stslex.core_navigation.destinations.TodoDestination
import com.stslex.feature_todo.R
import com.stslex.feature_todo.ui.TodoRoute

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.todoGraph() {
    composable(
        route = TodoDestination.route,
        enterTransition = {
            when (initialState.destination.route) {
                NoteListDestination.destination ->
                    slideIntoContainer(
                        AnimatedContentScope.SlideDirection.Left,
                        animationSpec = tween(700)
                    )

                else -> null
            }
        },
        exitTransition = {
            when (targetState.destination.route) {
                NoteListDestination.destination ->
                    slideOutOfContainer(
                        AnimatedContentScope.SlideDirection.Right,
                        animationSpec = tween(700)
                    )

                else -> null
            }
        }
    ) {
        TodoRoute()
    }
}

val todoTopLevelDestination = TopLevelDestination(
    destination = TodoDestination.route,
    route = TodoDestination.route,
    selectedIcon = Icons.Filled.Notifications,
    unselectedIcon = Icons.Outlined.Notifications,
    iconTextId = R.string.todo_label
)