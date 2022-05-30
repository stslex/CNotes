plugins {
    id("cnotes.android.library")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-firebase"))
    implementation(libs.koin.android)
    api(libs.kotlinx.coroutines.android)
}

android {
    namespace = "com.stslex.core_coroutines"
}