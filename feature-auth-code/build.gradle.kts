plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(project(":core-coroutines"))
    implementation(project(":core-firebase"))
    implementation(project(":core-firebase-auth"))
    testImplementation(project(mapOf("path" to ":core-firebase")))
    testImplementation(project(mapOf("path" to ":core-firebase")))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(koin.androidx.compose)
        implementation(androidx.core.kts)
    }
}

android {
    namespace = "com.example.feature_auth_code"
}