plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
}

dependencies {
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(libs.androidx.core.kts)
}

android {
    namespace = "com.stslex.feature_todo"
}