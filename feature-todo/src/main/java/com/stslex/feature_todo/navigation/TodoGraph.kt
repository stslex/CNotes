package com.stslex.feature_todo.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Notifications
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.stslex.core_navigation.TopLevelDestination
import com.stslex.feature_todo.R
import com.stslex.feature_todo.ui.TodoRoute

fun NavGraphBuilder.todoGraph() {
    composable(route = TodoDestination.route) {
        TodoRoute()
    }
}

val TODO_TOP_LEVEL_DESTINATION = TopLevelDestination(
    route = TodoDestination.route,
    selectedIcon = Icons.Filled.Notifications,
    unselectedIcon = Icons.Outlined.Notifications,
    iconTextId = R.string.todo_label
)