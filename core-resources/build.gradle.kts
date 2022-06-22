plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.stslex.core_resources"
}

dependencies {
    implementation(project(":core-test"))
}