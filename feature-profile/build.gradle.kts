plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(project(":core-coroutines"))
    implementation(project(":core-firebase"))
    implementation(project(":core-data-source"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.androidx.compose)
        implementation(androidx.core.kts)
    }
}

android {
    namespace = "com.stslex.feature_profile"
}