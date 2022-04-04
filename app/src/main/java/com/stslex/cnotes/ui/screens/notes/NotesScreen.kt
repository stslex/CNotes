package com.stslex.cnotes.ui.screens.notes

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.cnotes.ui.common_elements.ExtendableFloatingActionButton
import com.stslex.cnotes.ui.navigation.NavScreen
import kotlinx.coroutines.ExperimentalCoroutinesApi

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
    Scaffold(
        floatingActionButton = notesFab(navController, lazyListState)
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                state = lazyListState
            ) {
                items(pagingItems) { item ->
                    item?.let { NotePagingItem(it, navController) }
                }
            }
        }
    }
}


fun notesFab(
    navController: NavController,
    lazyListState: LazyListState
): @Composable () -> Unit = {
    ExtendableFloatingActionButton(
        extended = !lazyListState.isScrollInProgress,
        text = { Text(text = "create") },
        icon = { Icon(imageVector = Icons.Default.Create, contentDescription = "create") },
        onClick = { navController.navigate(NavScreen.EditNoteScreen.route) }
    )
}

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@Preview
@Composable
fun NotesPreview() {
    NotesScreen(rememberNavController())
}