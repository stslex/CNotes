plugins {
    id("cnotes.android.library")
}

dependencies {
    implementation(project(":core-test"))
    with(libs) {
        api(androidx.navigation.compose)
    }
}

android {
    namespace = "com.stslex.core_navigation"
}