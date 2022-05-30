plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(libs.androidx.core.kts)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

android {
    namespace = "com.stslex.feature_todo"
}