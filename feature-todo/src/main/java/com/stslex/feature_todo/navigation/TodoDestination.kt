package com.stslex.feature_todo.navigation

import com.stslex.core_navigation.AppNavigationDestination

object TodoDestination : AppNavigationDestination {
    override val route: String
        get() = "todo_route"
    override val destination: String
        get() = "todo_destination"
}