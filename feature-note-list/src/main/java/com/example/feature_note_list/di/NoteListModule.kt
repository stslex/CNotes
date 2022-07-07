package com.example.feature_note_list.di

import androidx.paging.PagingConfig
import com.example.feature_note_list.data.abstraction.NoteListRepository
import com.example.feature_note_list.data.realisation.NoteListRepositoryImpl
import com.example.feature_note_list.ui.NotesViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class NoteListModule {
    val module = module {
        single { PagingConfig(10) }
        singleOf(::NoteListRepositoryImpl) { bind<NoteListRepository>() }
        viewModelOf(::NotesViewModel)
    }
}
