package com.stslex.cnotes.ui.navigation

sealed class NavScreen(val route: String) {
    object NotesScreen : NavScreen("Notes")
    object ToDoScreen : NavScreen("ToDo")
    object EditNoteScreen : NavScreen("EditNote")
}
