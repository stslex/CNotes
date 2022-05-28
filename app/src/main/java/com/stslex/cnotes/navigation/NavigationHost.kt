package com.stslex.cnotes.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.feature_note_list.navigation.NoteListDestination
import com.example.feature_note_list.navigation.noteListGraph
import com.stslex.feature_single_note.navigation.SingleNoteDestination
import com.stslex.feature_single_note.navigation.singleNoteGraph
import com.stslex.feature_todo.navigation.todoGraph

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = NoteListDestination.route
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        noteListGraph(
            openSingleNote = { navController.navigate("${SingleNoteDestination.route}/$it") }
        ) {
            singleNoteGraph()
        }
        todoGraph()
    }
}