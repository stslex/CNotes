package com.stslex.cnotes.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.stslex.cnotes.ui.screens.edit_note.EditNoteScreen
import com.stslex.cnotes.ui.screens.notes.NotesScreen
import com.stslex.cnotes.ui.screens.todo.ToDoScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun NavigationHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavScreen.NotesScreen.route
    ) {
        composable(route = NavScreen.NotesScreen.route) {
            NotesScreen(navController = navController)
        }
        composable(route = NavScreen.ToDoScreen.route) {
            ToDoScreen()
        }
        composable(route = NavScreen.EditNoteScreen.route) {
            EditNoteScreen(navController = navController)
        }
    }
}