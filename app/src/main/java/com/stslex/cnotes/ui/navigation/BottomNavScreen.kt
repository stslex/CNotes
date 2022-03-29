package com.stslex.cnotes.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(
    val route: String,
    val icon: ImageVector
) {
    object NotesScreen : BottomNavScreen(
        NavScreen.NotesScreen.route,
        Icons.Filled.List
    )

    object ToDoScreen : BottomNavScreen(
        NavScreen.ToDoScreen.route,
        Icons.Filled.Notifications
    )
}