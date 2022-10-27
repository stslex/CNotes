package com.stslex.feature_note_list.navigation

interface NoteListRouter {
    val openSingleNote: (noteId: Int) -> Unit
    val openProfile: () -> Unit
    val openAuthPhoneNumber: () -> Unit
}