plugins {
    id("cnotes.android.library")
}

dependencies {
    implementation(project(":core-test"))
    implementation(libs.koin.android)
}

android {
    namespace = "com.stslex.core_coroutines"
}