package com.stslex.feature_note_list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.material3.TopAppBarScrollState
import androidx.compose.material3.rememberTopAppBarScrollState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.feature_note_list.ui.fab.NotesFab
import com.stslex.feature_note_list.ui.note.NotePagingItem
import com.stslex.feature_note_list.ui.top_bar.NoteMediumTopAppBar
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = getViewModel(),
    lazyListState: LazyListState = rememberLazyListState(),
    scrollState: TopAppBarScrollState = rememberTopAppBarScrollState()
) {
    val pagingItems = viewModel.allNotes.collectAsLazyPagingItems()
    val scrollBehavior = enterAlwaysScrollBehavior(scrollState) { pagingItems.itemCount >= 6 }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            AnimatedVisibility(visible = viewModel.isCreateButtonVisible.value) {
                NotesFab(
                    lazyListState = lazyListState,
                    viewModel = viewModel
                )
            }
        },
        topBar = {
            NoteMediumTopAppBar(
                scrollBehavior = scrollBehavior,
                viewModel = viewModel
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        Surface(
            modifier = Modifier.padding(paddingValues)
        ) {
            LazyColumn(
                state = lazyListState
            ) {
                items(pagingItems, key = { it.id }) { item ->
                    item?.let {
                        NotePagingItem(
                            note = item,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun NotesPreview() {
    NotesScreen()
}