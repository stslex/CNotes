package com.stslex.feature_profile.di

import com.stslex.core_model.common.MapperName
import com.stslex.feature_profile.data.abstraction.ProfileRepository
import com.stslex.feature_profile.data.implementation.ProfileRepositoryImpl
import com.stslex.feature_profile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

class ProfileModule {
    val module = module {
        viewModelOf(::ProfileViewModel)

        factory<ProfileRepository> {
            ProfileRepositoryImpl(
                remoteNotesService = get(),
                firebaseInitializer = get(),
                mapperNoteSize = get(named(MapperName.VALUE_ENTITY_SIZE)),
                transformerNoteEquals = get(),
                mapperNoteListRemote = get(named(MapperName.ENTITY_MAP_LIST)),
                noteDao = get(),
                flowMapper = get()
            )
        }
    }
}
