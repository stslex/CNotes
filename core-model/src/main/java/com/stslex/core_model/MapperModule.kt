package com.stslex.core_model

import androidx.paging.PagingData
import com.stslex.core.Mapper
import com.stslex.core.ValueState
import com.stslex.core_model.mapper.*
import com.stslex.core_model.model.NoteDynamicUI
import com.stslex.core_model.model.NoteEntity
import com.stslex.core_model.model.NoteUI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class MapperModule {

    @Provides
    fun providesMapperEntityDynamicUI(): Mapper.Data<NoteEntity, NoteDynamicUI> =
        MapperEntityDynamicUI()

    @Provides
    fun providesMapperEntityUI(): Mapper.Data<NoteEntity, NoteUI> = MapperEntityUI()

    @Provides
    fun providesMapperNoteUIEntity(): Mapper.Data<NoteUI, NoteEntity> = MapperNoteUIEntity()

    @Provides
    fun providesMapperPagingEntityDynamicUI(mapper: Mapper.Data<NoteEntity, NoteDynamicUI>): Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDynamicUI>> =
        MapperPagingEntityDynamicUI(mapper)

    @Provides
    fun providesMapperValueNoteEntityUI(mapper: Mapper.Data<NoteEntity, NoteUI>): Mapper.ToUI<NoteEntity, ValueState<NoteUI>> =
        MapperValueNoteEntityUI(mapper)
}