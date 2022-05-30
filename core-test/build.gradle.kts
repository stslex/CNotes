plugins {
    id("cnotes.android.library")
}

dependencies {

    api("org.mockito:mockito-core:4.5.1")

    with(libs) {
        api(junit)
        api(androidx.test.junit)
        api(androidx.test.espresso.core)
        api(androidx.compose.ui.test.junit4)
        api(koin.test)
        api(koin.test.junit4)
        api(robolectric)
        api(kotlinx.coroutines.test)
        debugApi(leakcanary)
    }
}

android {
    namespace = "com.stslex.core_test"
}