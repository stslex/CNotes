package com.stslex.cnotes.ui.screens.notes

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun NotesScreen(viewModel: NotesViewModel= hiltViewModel()) {
    Text(text = "NotesScreen2")
}

@Preview
@Composable
fun NotesPreview(){
    NotesScreen()
}