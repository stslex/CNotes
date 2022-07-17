package com.stslex.core_firebase.realisation

import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.stslex.core_firebase.BuildConfig
import com.stslex.core_firebase.utils.realisation.FirebaseAppInitialisationUtilImpl
import org.junit.After
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class FirebaseAppInitialisationUtilImplTest {

    private val context = RuntimeEnvironment.getApplication()
    private val options = FirebaseOptions.Builder()
        .setProjectId(BuildConfig.FIREBASE_PROJECT_ID)
        .setApplicationId(BuildConfig.FIREBASE_APPLICATION_ID)
        .setApiKey(BuildConfig.FIREBASE_API_KEY)
        .setDatabaseUrl(BuildConfig.FIREBASE_DATABASE_URL)
        .build()

    @Test
    fun invoke() {
        val fbInit = FirebaseAppInitialisationUtilImpl(context, options)
        fbInit.invoke()
        Assert.assertFalse(FirebaseApp.getApps(context).isEmpty())
    }

    @After
    fun clearFirebase() {
        FirebaseApp.clearInstancesForTest()
    }
}