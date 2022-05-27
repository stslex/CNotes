plugins {
    id("cnotes.android.library")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core-test"))
}

android {
    namespace = "com.stslex.core_coroutines"
}