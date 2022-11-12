package com.stslex.core_navigation

import androidx.compose.ui.graphics.vector.ImageVector

data class TopLevelDestination(
    val destination: String,
    val route: String,
    private val selectedIcon: ImageVector,
    private val unselectedIcon: ImageVector,
    val iconTextId: Int
) {

    val icon: (isSelected: Boolean) -> ImageVector
        get() = { isSelected ->
            if (isSelected) {
                selectedIcon
            } else {
                unselectedIcon
            }
        }
}