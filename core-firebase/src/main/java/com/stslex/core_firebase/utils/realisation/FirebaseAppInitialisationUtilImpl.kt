package com.stslex.core_firebase.utils.realisation

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.stslex.core_firebase.utils.abstraction.FirebaseAppInitialisationUtil

class FirebaseAppInitialisationUtilImpl(
    private val context: Context,
    private val firebaseOptions: FirebaseOptions
) : FirebaseAppInitialisationUtil {

    override fun invoke() {
        if (FirebaseApp.getApps(context).isEmpty()) {
            FirebaseApp.initializeApp(context, firebaseOptions)
        }
    }
}