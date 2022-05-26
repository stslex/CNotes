package com.example.feature_note_list.di

import com.example.feature_note_list.data.NoteRepository
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
    fun bindsNoteRepository(repository: NoteRepository.Base): NoteRepository
}