package com.stslex.cnotes.di

import android.content.Context
import com.stslex.cnotes.data.database.NoteDao
import com.stslex.cnotes.data.database.NoteRoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNoteDao(context: Context): NoteDao = NoteRoomDatabase.getDatabase(
        context = context,
        scope = CoroutineScope(SupervisorJob())
    ).noteDao()
}