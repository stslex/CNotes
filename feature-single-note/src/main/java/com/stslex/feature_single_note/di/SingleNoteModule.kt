package com.stslex.feature_single_note.di

import com.stslex.core_model.common.MapperName
import com.stslex.core_model.common.MapperName.UI_ENTITY
import com.stslex.feature_single_note.data.abstraction.SingleNoteRepository
import com.stslex.feature_single_note.data.realisation.SingleNoteRepositoryImpl
import com.stslex.feature_single_note.ui.SingleNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

class SingleNoteModule {
    val module = module {
        factory<SingleNoteRepository> {
            SingleNoteRepositoryImpl(
                noteDao = get(),
                mapper = get(named(UI_ENTITY))
            )
        }

        viewModel {
            SingleNoteViewModel(
                dispatchers = get(),
                mapper = get(named(MapperName.VALUE_ENTITY_UI)),
                noteId = get(),
                repository = get(),
            )
        }
    }
}