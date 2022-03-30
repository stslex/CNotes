package com.stslex.cnotes.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(
    includes = [
        MapperModule::class,
        DatabaseModule::class,
        RepositoryModule::class
    ]
)
@InstallIn(SingletonComponent::class)
interface AppModule