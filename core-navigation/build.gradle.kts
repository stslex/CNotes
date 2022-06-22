plugins {
    id("cnotes.android.library")
}

dependencies {
    implementation(project(":core-test"))
    with(libs) {
        api(google.accompanist.navigation.animation)
    }
}

android {
    namespace = "com.stslex.core_navigation"
}