package com.stslex.feature_profile.di

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.stslex.core_firebase.FirebaseReferences.CHILD_NOTES
import com.stslex.core_firebase.FirebaseReferences.NODE_USERS
import com.stslex.core_firebase.FirebaseReferences.REFERENCE_NOTES
import com.stslex.feature_profile.data.ProfileRepository
import com.stslex.feature_profile.ui.ProfileViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module

val profileModule = module {

    singleOf(::ProfileViewModel)

    single<ProfileRepository> {
        ProfileRepository.Base(
            noteDao = get(),
            mapper = get(),
            reference = get(qualifier = named(REFERENCE_NOTES))
        )
    }

    single(named(REFERENCE_NOTES)) {
        get<FirebaseDatabase>().reference
            .child(NODE_USERS)
            .child(get<FirebaseUser>().uid)
            .child(CHILD_NOTES)
    }
}