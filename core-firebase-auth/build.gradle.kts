plugins {
    id("cnotes.android.library")
    id("com.google.devtools.ksp")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-firebase"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.core.kts)
    }
}

android {
    namespace = "com.stslex.core_firebase_auth"
}