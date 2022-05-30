plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(project(":core-model"))
    implementation(project(":core-data-source"))
    implementation(project(":core-coroutines"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.androidx.compose)
        implementation(androidx.core.kts)
        implementation(androidx.paging.runtime)
        implementation(androidx.paging.compose)
    }
}

android {
    namespace = "com.example.feature_note_list"
}