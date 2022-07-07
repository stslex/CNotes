package com.stslex.feature_single_note.di

import com.stslex.feature_single_note.data.abstraction.SingleNoteRepository
import com.stslex.feature_single_note.data.realisation.SingleNoteRepositoryImpl
import com.stslex.feature_single_note.ui.SingleNoteViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

class SingleNoteModule{
    val module = module {
        singleOf(::SingleNoteRepositoryImpl) { bind<SingleNoteRepository>() }
        viewModelOf(::SingleNoteViewModel)
    }
}