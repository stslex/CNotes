package com.stslex.cnotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.stslex.cnotes.ui.screens.MainScreen

@Composable
fun NavigationHost(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.MainScreen.route
    ) {
        composable(route = NavScreen.MainScreen.route) {
            MainScreen()
        }
    }
}