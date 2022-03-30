package com.stslex.cnotes.ui.screens.notes

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.cnotes.ui.model.NoteUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun NotesScreen(viewModel: NotesViewModel = hiltViewModel()) {
    val pagingItems = viewModel.getAllNotes().collectAsLazyPagingItems()
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(pagingItems) { item ->
            item?.let { NoteItem(it) }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun NoteItem(note: NoteUIModel) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = note.title())
            Text(text = note.content())
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun NotesPreview() {
    NotesScreen()
}