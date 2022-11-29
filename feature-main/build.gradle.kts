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
    implementation(project(":core-firebase"))
    implementation(project(":feature-note-list"))
    implementation(project(":feature-single-note"))
    implementation(project(":feature-todo"))
    implementation(project(":feature-profile"))
    implementation(project(":feature-auth-phonenumber"))
    implementation(project(":feature-auth-code"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.androidx.compose)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.core.kts)
        implementation(androidx.paging.runtime)
        implementation(androidx.paging.compose)
        implementation(androidx.core.google.shortcuts)
        implementation(google.accompanist.systemuicontroller)
    }
}

android {
    namespace = "com.stslex.feature_main"
}