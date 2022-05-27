package com.stslex.cnotes

import androidx.paging.PagingData
import com.example.feature_note_list.data.NoteListRepository
import com.example.feature_note_list.data.NoteListRepositoryImpl
import com.example.feature_note_list.ui.NotesViewModel
import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_coroutines.AppDispatchers
import com.stslex.core_data_source.NoteRoomDatabase
import com.stslex.core_model.mapper.*
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import com.stslex.feature_single_note.data.SingleNoteRepository
import com.stslex.feature_single_note.data.SingleNoteRepositoryImpl
import com.stslex.feature_single_note.ui.SingleNoteViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val singleNoteModule = module {

    single {
        NoteRoomDatabase.getDatabase(androidContext(), CoroutineScope(SupervisorJob())).noteDao()
    }

    single { AppDispatchers() }

    singleOf(::SingleNoteRepositoryImpl) { bind<SingleNoteRepository>() }
    singleOf(::NoteListRepositoryImpl) { bind<NoteListRepository>() }
    viewModelOf(::SingleNoteViewModel)
    viewModelOf(::NotesViewModel)


    single<Mapper.Data<NoteEntity, NoteDynamicUI>> { MapperEntityDynamicUI() }

    single<Mapper.Data<NoteEntity, NoteUI>> { MapperEntityUI() }

    single<Mapper.Data<NoteUI, NoteEntity>> { MapperNoteUIEntity() }

    single<Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>>> {
        MapperPagingEntityDynamicUI(get<Mapper.Data<NoteEntity, NoteDynamicUI>>())
    }

    single<Mapper.ToUI<NoteEntity, ValueState<NoteUI>>> { MapperValueNoteEntityUI(get<Mapper.Data<NoteEntity, NoteUI>>()) }
}