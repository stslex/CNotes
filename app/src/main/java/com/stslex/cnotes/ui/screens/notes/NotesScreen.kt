package com.stslex.cnotes.ui.screens.notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.cnotes.ui.model.NoteUIModel
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalAnimationApi
@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Composable
fun NotesScreen(
    navController: NavController,
    viewModel: NotesViewModel = hiltViewModel(),
    lazyListState: LazyListState = rememberLazyListState()
) {
    val pagingItems = viewModel.allNotes.collectAsLazyPagingItems()
    val selectedNotes = remember {
        mutableStateListOf<NoteUIModel>()
    }
    val deleteNotesFunction = viewModel::deleteNotesById
    Scaffold(
        floatingActionButton = notesFab(
            lazyListState = lazyListState,
            selectedNotes = selectedNotes,
            deleteNotesFunction = deleteNotesFunction,
            navController = navController
        )
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                state = lazyListState
            ) {
                items(pagingItems) { item ->
                    item?.let {
                        NotePagingItem(
                            note = it,
                            navController = navController,
                            selectedNotes = selectedNotes
                        )
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Preview
@Composable
fun NotesPreview() {
    NotesScreen(rememberNavController())
}