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
        implementation(koin.android)
        implementation(androidx.core.kts)
    }
}
android {
    namespace = "com.stslex.feature_single_note"
}