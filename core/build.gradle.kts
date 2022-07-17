plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.jacoco")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core-test"))
    implementation(libs.koin.android)
    api(libs.kotlinx.coroutines.android)
    implementation(libs.koin.core)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp)
}

android {
    namespace = "com.stslex.core"
}