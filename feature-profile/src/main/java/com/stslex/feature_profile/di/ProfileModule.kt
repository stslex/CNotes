package com.stslex.feature_profile.di

import com.stslex.feature_profile.data.ProfileRepositoryOld
import com.stslex.feature_profile.domain.abstraction.*
import com.stslex.feature_profile.domain.realisation.*
import com.stslex.feature_profile.ui.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val profileModule = module {
    viewModelOf(::ProfileViewModel)
    singleOf(ProfileRepository::Base) { bind<ProfileRepository>() }
    singleOf(::GetLocalNotesSizeInteractorImpl) { bind<GetLocalNotesSizeInteractor>() }
    singleOf(::GetRemoteNotesSizeInteractorImpl) { bind<GetRemoteNotesSizeInteractor>() }
    singleOf(::GetSyncNotesSizeInteractorImpl) { bind<GetSyncNotesSizeInteractor>() }
    singleOf(::SignOutInteractorImpl) { bind<SignOutInteractor>() }
    singleOf(::SynchronizeNotesInteractorImpl) { bind<SynchronizeNotesInteractor>() }
    singleOf(::DownloadNotesInteractorImpl) { bind<DownloadNotesInteractor>() }
    singleOf(::CheckUserAuthInteractorImpl) { bind<CheckUserAuthInteractor>() }
}