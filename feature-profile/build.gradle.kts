plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-model"))
    implementation(project(":core-navigation"))
    implementation(project(":core-coroutines"))
    implementation(project(":core-firebase"))
    implementation(project(":core-data-source"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.androidx.compose)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.core.kts)
    }
}

android {
    namespace = "com.stslex.feature_profile"
}