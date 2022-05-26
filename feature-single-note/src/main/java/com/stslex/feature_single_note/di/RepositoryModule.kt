package com.stslex.feature_single_note.di

import com.stslex.feature_single_note.data.NoteRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Singleton
    @Binds
    fun bindsRepository(repository: NoteRepository.Base): NoteRepository
}