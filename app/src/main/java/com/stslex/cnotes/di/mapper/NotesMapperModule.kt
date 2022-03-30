package com.stslex.cnotes.di.mapper

import androidx.paging.PagingData
import com.stslex.cnotes.core.PagingDataMapper
import com.stslex.cnotes.data.entity.NoteEntity
import com.stslex.cnotes.data.mapper.MapperNoteEntityToData
import com.stslex.cnotes.data.model.NoteDataModel
import com.stslex.cnotes.ui.mapper.MapperNoteDataToUI
import com.stslex.cnotes.ui.model.NoteUIModel
import com.stslex.core.Mapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class NotesMapperModule {

    @Provides
    fun providesMapperNoteEntityToData(): Mapper.Data<NoteEntity, NoteDataModel> =
        MapperNoteEntityToData()

    @Provides
    fun providesMapperNoteEntityToDataPaging(
        mapper: Mapper.Data<NoteEntity, NoteDataModel>
    ): Mapper.Data<PagingData<NoteEntity>, PagingData<NoteDataModel>> =
        PagingDataMapper(mapper = mapper)

    @Provides
    fun providesMapperNoteDataToUI(): Mapper.Data<NoteDataModel, NoteUIModel> =
        MapperNoteDataToUI()

    @Provides
    fun providesMapperNoteDataToUIPaging(
        mapper: Mapper.Data<NoteDataModel, NoteUIModel>
    ): Mapper.Data<PagingData<NoteDataModel>, PagingData<NoteUIModel>> =
        PagingDataMapper(mapper = mapper)
}