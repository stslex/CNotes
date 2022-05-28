package com.example.feature_note_list.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.material3.TopAppBarDefaults.enterAlwaysScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.stslex.core_model.model.NoteDynamicUI
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreen(
    openSingleNote: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = getViewModel(),
    lazyListState: LazyListState = rememberLazyListState()
) {
    val pagingItems = viewModel.allNotes.collectAsLazyPagingItems()
    val selectedNotes = remember {
        mutableStateListOf<NoteDynamicUI>()
    }
    val deleteNotesFunction = viewModel::deleteNotesById

    val scrollBehavior = enterAlwaysScrollBehavior { true }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = {
            NotesFab(
                lazyListState = lazyListState,
                selectedNotes = selectedNotes,
                deleteNotesFunction = deleteNotesFunction,
                openSingleNote = openSingleNote
            )
        },
        topBar = noteMediumTopAppBar(scrollBehavior),
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
                            openSingleNote = openSingleNote
                        )
                    }
                }
            }
        }
    }
}

fun noteMediumTopAppBar(scrollBehavior: TopAppBarScrollBehavior): @Composable () -> Unit = {
    MediumTopAppBar(
        title = {
            Text(
                text = "Notes",
                style = MaterialTheme.typography.titleLarge
            )
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "account"
                )
            }
        },
        actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = "account"
                )
            }
        },
        scrollBehavior = scrollBehavior
    )
}

@Preview
@Composable
fun NotesPreview() {
    NotesScreen({})
}