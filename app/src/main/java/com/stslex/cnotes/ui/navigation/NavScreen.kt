package com.stslex.cnotes.ui.navigation

sealed class NavScreen(val route: String) {
    object MainScreen : NavScreen("MainScreen")
}
