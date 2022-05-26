package com.stslex.core_data_source.di

import android.content.Context
import androidx.room.Room
import com.stslex.core_data_source.base.BaseRoomCallback
import com.stslex.core_data_source.base.BaseRoomDatabase
import com.stslex.core_data_source.database.NoteDao
import com.stslex.core_data_source.database.NoteDatabaseCallback
import com.stslex.core_data_source.database.NoteRoomDatabase
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
        roomCallback: BaseRoomCallback<NoteDao>
    ): BaseRoomDatabase<NoteDao> = Room.databaseBuilder(
        context.applicationContext,
        NoteRoomDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .addCallback(roomCallback)
        .build()

    @Singleton
    @Provides
    fun provideForecastDao(db: BaseRoomDatabase<NoteDao>): NoteDao = db.dao()

    @Provides
    fun providesNoteRoomCallback(roomDatabase: Lazy<BaseRoomDatabase<NoteDao>>): BaseRoomCallback<NoteDao> =
        NoteDatabaseCallback(CoroutineScope(SupervisorJob()), roomDatabase)

    companion object {
        private const val DATABASE_NAME: String = "note_database"
    }
}