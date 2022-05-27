plugins {
    id("cnotes.android.library")
}

dependencies {
    with(libs) {
        api(androidx.navigation.compose)
    }
}

android {
    namespace = "com.stslex.core_navigation"
}