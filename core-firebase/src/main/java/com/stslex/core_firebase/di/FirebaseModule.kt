package com.stslex.core_firebase.di

import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stslex.core_firebase.*
import com.stslex.core_firebase.core.FbNames
import com.stslex.core_firebase.core.FirebaseReferences
import com.stslex.core_firebase.data.abstraction.FirebaseAuthRepository
import com.stslex.core_firebase.utils.abstraction.FirebaseAppInitialisationUtil
import com.stslex.core_firebase.data.abstraction.FirebaseNotesService
import com.stslex.core_firebase.data.realisation.FirebaseAuthRepositoryImpl
import com.stslex.core_firebase.utils.abstraction.ICoroutinesTaskHandler
import com.stslex.core_firebase.utils.realisation.CoroutinesTaskHandler
import com.stslex.core_firebase.utils.realisation.FirebaseAppInitialisationUtilImpl
import com.stslex.core_firebase.data.realisation.FirebaseNotesServiceImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.Single
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.util.concurrent.Executors

@Single
class FirebaseModule {

    val module = module {

        single {
            FirebaseOptions.Builder()
                .setProjectId(BuildConfig.FIREBASE_PROJECT_ID)
                .setApplicationId(BuildConfig.FIREBASE_APPLICATION_ID)
                .setApiKey(BuildConfig.FIREBASE_API_KEY)
                .setDatabaseUrl(BuildConfig.FIREBASE_DATABASE_URL)
                .build()
        }

        factory<ICoroutinesTaskHandler<Void>>(named(FbNames.VOID)) {
            CoroutinesTaskHandler(executor = Executors.newSingleThreadExecutor())
        }

        factory {
            Firebase.firestore
        }

        factory {
            Firebase.database
        }

        factory<FirebaseAppInitialisationUtil> {
            FirebaseAppInitialisationUtilImpl(androidContext(), get())
        }

        singleOf(::FirebaseAuthRepositoryImpl) { bind<FirebaseAuthRepository>() }

        single<FirebaseNotesService> {
            FirebaseNotesServiceImpl(
                coroutinesTaskHandler = get(named(FbNames.VOID)),
                reference = lazy {
                    get<FirebaseDatabase>().reference.child(FirebaseReferences.NODE_USERS)
                        .child(Firebase.auth.currentUser?.uid ?: "")
                        .child(FirebaseReferences.CHILD_NOTES)
                })
        }
    }
}
