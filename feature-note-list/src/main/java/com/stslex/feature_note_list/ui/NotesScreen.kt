package com.stslex.feature_note_list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    scrollState: TopAppBarState = rememberTopAppBarState()
) {
    viewModel.isCreateButtonVisible.value = true
    val pagingItems = viewModel.allNotes.collectAsLazyPagingItems()
    val scrollBehavior = enterAlwaysScrollBehavior(
        state = scrollState,
        canScroll = { pagingItems.itemCount >= 6 }
    )
    //TODO Add Error drawing
    val errorState = viewModel.errorState.collectAsState()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            AnimatedVisibility(
                visible = viewModel.isCreateButtonVisible.value
            ) {
                NotesFab(
                    lazyListState = lazyListState,
                    selectedNotes = viewModel.selectedNotes,
                    onCreateButtonClicked = viewModel::onCreateButtonClicked
                )
            }
        },
        topBar = {
            NoteMediumTopAppBar(
                scrollBehavior = scrollBehavior,
                selectedNotes = viewModel.selectedNotes,
                onProfileButtonClicked = viewModel::onProfileButtonClicked
            )
        },
        floatingActionButtonPosition = FabPosition.End
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues),
            state = lazyListState
        ) {
            items(
                items = pagingItems,
                key = { it.id }
            ) { item ->
                item?.let {
                    NotePagingItem(
                        note = item,
                        onNoteClick = {
                            viewModel.onSingleNoteClick(item)
                        },
                        onNoteLongClick = {
                            viewModel.onNotesSelect(item)
                        }
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NotesPreview() {
    NotesScreen()
}