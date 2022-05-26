package com.stslex.core_model.di

import com.stslex.core_model.data.MapperNoteDataEntity
import com.stslex.core_model.data.MapperNoteDataPaging
import com.stslex.core_model.data.MapperNoteEntityData
import com.stslex.core_model.ui.MapperNoteDataUI
import com.stslex.core_model.ui.MapperNoteDataUIPaging
import com.stslex.core_model.ui.MapperNoteUIData
import com.stslex.core_model.ui.MapperNoteValueDataUI
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface NotesMapperModule {

    @Binds
    fun providesMapperNoteData(mapper: MapperNoteEntityData.Base): MapperNoteEntityData

    @Binds
    fun providesMapperNoteDataPaging(mapper: MapperNoteDataPaging.Base): MapperNoteDataPaging

    @Binds
    fun providesMapperNoteUI(mapper: MapperNoteDataUI.Base): MapperNoteDataUI

    @Binds
    fun providesMapperNoteUIPaging(mapper: MapperNoteDataUIPaging.Base): MapperNoteDataUIPaging

    @Binds
    fun providesMapperNoteDataEntity(mapper: MapperNoteDataEntity.Base): MapperNoteDataEntity

    @Binds
    fun providesMapperNoteUIData(mapper: MapperNoteUIData.Base): MapperNoteUIData

    @Binds
    fun providesMapperNoteValueDataUI(mapper: MapperNoteValueDataUI.Base): MapperNoteValueDataUI
}