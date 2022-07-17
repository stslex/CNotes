package com.stslex.feature_note_list.di

import androidx.paging.PagingConfig
import com.stslex.feature_note_list.ui.NotesViewModel
import com.stslex.core_model.common.MapperName
import com.stslex.feature_note_list.data.abstraction.NoteListRepository
import com.stslex.feature_note_list.data.realisation.NoteListRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class NoteListModule {
    val module = module {
        single { PagingConfig(10) }
        factoryOf(::NoteListRepositoryImpl) { bind<NoteListRepository>() }

        viewModel {
            NotesViewModel(
                noteRepository = get(),
                noteMapper = get(named(MapperName.PAGING_ENTITY_DYNAMIC)),
                dispatchers = get()
            )
        }
    }
}
