plugins {
    id("cnotes.android.library")
    kotlin("kapt")
}

dependencies {
    with(libs) {
        api(hilt.navigation.compose)
        api(libs.androidx.navigation.compose)
        implementation(libs.hilt.android.core)
        kapt(hilt.android.compiler)
    }
}

android {
    namespace = "com.stslex.core_navigation"
}