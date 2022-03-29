package com.stslex.cnotes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stslex.cnotes.ui.screens.notes.NotesScreen
import com.stslex.cnotes.ui.screens.todo.ToDoScreen

@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.NotesScreen.route
    ) {
        composable(route = NavScreen.NotesScreen.route) {
            NotesScreen()
        }
        composable(route = NavScreen.ToDoScreen.route) {
            ToDoScreen()
        }
    }
}