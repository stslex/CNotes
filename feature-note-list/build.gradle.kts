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
    implementation(project(":core-model"))
    implementation(project(":core-local-data-source"))
    implementation(project(":core-resources"))
    implementation(project(":core-firebase"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.androidx.compose)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.core.kts)
        implementation(androidx.paging.runtime)
        implementation(androidx.paging.compose)
    }
}

android {
    namespace = "com.example.feature_note_list"
}