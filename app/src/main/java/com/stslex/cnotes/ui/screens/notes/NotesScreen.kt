package com.stslex.cnotes.ui.screens.notes

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
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

    val scrollBehavior = enterAlwaysScrollBehavior { true }

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        floatingActionButton = notesFab(
            lazyListState = lazyListState,
            selectedNotes = selectedNotes,
            deleteNotesFunction = deleteNotesFunction,
            navController = navController
        ),
        topBar = noteMediumTopAppBar(scrollBehavior),
        floatingActionButtonPosition = FabPosition.End
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


@ExperimentalMaterial3Api
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

@ExperimentalFoundationApi
@ExperimentalCoroutinesApi
@ExperimentalMaterial3Api
@ExperimentalAnimationApi
@Preview
@Composable
fun NotesPreview() {
    NotesScreen(rememberNavController())
}