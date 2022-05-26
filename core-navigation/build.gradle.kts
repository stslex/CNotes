plugins {
    id("cnotes.android.library")
    kotlin("kapt")
}

dependencies {
    with(libs) {
        api(hilt.navigation.compose)
        api(androidx.navigation.compose)
        api(accompanist.navigation.animation)
        implementation(hilt.android.core)
        kapt(hilt.android.compiler)
    }
}

android {
    namespace = "com.stslex.core_navigation"
}