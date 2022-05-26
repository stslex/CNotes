plugins {
    id("cnotes.android.library")
}

dependencies {
    with(libs) {
        api(junit)
        api(androidx.test.junit)
        api(androidx.test.espresso.core)
        api(androidx.compose.ui.test.junit4)
    }
}

android {
    namespace = "com.stslex.core_test"
}