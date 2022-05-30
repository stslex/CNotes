plugins {
    id("cnotes.android.library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(androidx.core.kts)
        api(google.firebase.auth)
        api(google.firebase.firestore)
        api(google.firebase.database)
    }
}

android {
    namespace = "com.stslex.core_firebase"
}