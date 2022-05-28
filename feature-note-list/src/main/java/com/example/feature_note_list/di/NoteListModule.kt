package com.example.feature_note_list.di

import com.example.feature_note_list.data.NoteListRepository
import com.example.feature_note_list.data.NoteListRepositoryImpl
import com.example.feature_note_list.ui.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val noteListModule = module {
    singleOf(::NoteListRepositoryImpl) { bind<NoteListRepository>() }
    viewModelOf(::NotesViewModel)
}