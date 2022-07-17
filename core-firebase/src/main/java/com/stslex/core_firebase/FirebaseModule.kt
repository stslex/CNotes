package com.stslex.core_firebase

import com.google.firebase.FirebaseOptions
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.stslex.core_firebase.abstraction.FirebaseAppInitialisationUtil
import com.stslex.core_firebase.abstraction.ICoroutinesTaskHandler
import com.stslex.core_firebase.realisation.CoroutinesTaskHandler
import com.stslex.core_firebase.realisation.FirebaseAppInitialisationUtilImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.annotation.Single
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
            FirebaseAppInitialisationUtilImpl(androidContext(), get(named(FbNames.VOID)))
        }
    }
}
