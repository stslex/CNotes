package com.stslex.feature_note_list.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.feature_note_list.ui.fab.NotesFab
import com.stslex.feature_note_list.ui.note.NotePagingItem
import com.stslex.feature_note_list.ui.top_bar.NoteMediumTopAppBar
import com.stslex.core_model.model.NoteDynamicUI
import org.koin.androidx.compose.getViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    openSingleNote: (Int) -> Unit,
    openProfile: () -> Unit,
    openAuthPhoneNumber: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = getViewModel(),
    lazyListState: LazyListState = rememberLazyListState(),
    scrollState: TopAppBarScrollState = rememberTopAppBarScrollState()
) {
    val pagingItems = viewModel.allNotes.collectAsLazyPagingItems()
    val selectedNotes = remember { mutableStateListOf<NoteDynamicUI>() }
    val deleteNotesFunction = viewModel::deleteNotesById
    val scrollBehavior = enterAlwaysScrollBehavior(scrollState) { pagingItems.itemCount >= 6 }
    val isButtonVisible = remember { mutableStateOf(true) }
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            AnimatedVisibility(visible = isButtonVisible.value) {
                NotesFab(
                    lazyListState = lazyListState,
                    selectedNotes = selectedNotes,
                    deleteNotesFunction = deleteNotesFunction,
                    openSingleNote = openSingleNote,
                    isButtonVisible = isButtonVisible
                )
            }
        },
        topBar = {
            NoteMediumTopAppBar(
                scrollBehavior = scrollBehavior,
                openAccount = if (viewModel.isUserAuth) openProfile else openAuthPhoneNumber,
                selectedNotes = selectedNotes
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
                            selectedNotes = selectedNotes,
                            openSingleNote = openSingleNote,
                            isButtonVisible = isButtonVisible
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
    NotesScreen({}, {}, {})
}