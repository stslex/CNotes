package com.stslex.cnotes.di

import com.stslex.cnotes.data.repository.NoteRepository
import com.stslex.cnotes.data.repository.NoteRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsNoteRepository(repository: NoteRepositoryImpl): NoteRepository
}