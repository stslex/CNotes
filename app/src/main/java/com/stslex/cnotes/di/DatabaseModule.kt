package com.stslex.cnotes.di

import android.app.Application
import com.stslex.cnotes.data.database.NoteDao
import com.stslex.cnotes.data.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDao(application: Application): NoteDao = NoteRoomDatabase.getDatabase(
        context = application,
        scope = CoroutineScope(SupervisorJob())
    ).noteDao()
}