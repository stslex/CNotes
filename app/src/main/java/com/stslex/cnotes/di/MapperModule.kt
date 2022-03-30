package com.stslex.cnotes.di

import com.stslex.cnotes.di.mapper.NotesMapperModule
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module(
    includes = [
        NotesMapperModule::class
    ]
)
@InstallIn(ActivityComponent::class)
interface MapperModule