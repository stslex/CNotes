package com.stslex.core_navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelDestination(
    val destination: String,
    val route: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val iconTextId: Int
)