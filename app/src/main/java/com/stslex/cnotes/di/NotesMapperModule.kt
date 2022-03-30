package com.stslex.cnotes.di

import com.stslex.cnotes.data.mapper.MapperNoteData
import com.stslex.cnotes.data.mapper.MapperNoteDataPaging
import com.stslex.cnotes.ui.mapper.MapperNoteUI
import com.stslex.cnotes.ui.mapper.MapperNoteUIPaging
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface NotesMapperModule {

    @Binds
    fun providesMapperNoteData(mapper: MapperNoteData.Base): MapperNoteData

    @Binds
    fun providesMapperNoteDataPaging(mapper: MapperNoteDataPaging.Base): MapperNoteDataPaging

    @Binds
    fun providesMapperNoteUI(mapper: MapperNoteUI.Base): MapperNoteUI

    @Binds
    fun providesMapperNoteUIPaging(mapper: MapperNoteUIPaging.Base): MapperNoteUIPaging
}