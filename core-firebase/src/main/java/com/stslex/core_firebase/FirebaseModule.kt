package com.stslex.core_firebase

import com.google.firebase.FirebaseOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module

val firebaseModule = module {

    single {
        FirebaseOptions.Builder()
            .setProjectId(BuildConfig.FIREBASE_PROJECT_ID)
            .setApplicationId(BuildConfig.FIREBASE_APPLICATION_ID)
            .setApiKey(BuildConfig.FIREBASE_API_KEY)
            .setDatabaseUrl(BuildConfig.FIREBASE_DATABASE_URL)
            .build()
    }

    single {
        Firebase.auth.currentUser!!
    }

    single {
        Firebase.firestore
    }

    single {
        Firebase.database
    }
}