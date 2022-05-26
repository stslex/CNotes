plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-test"))
    implementation(project(":core-navigation"))
    implementation(project(":core-model"))
    implementation(project(":core-data-source"))

    with(libs) {
        implementation(hilt.android.core)
        kapt(hilt.android.compiler)
        implementation(androidx.core.kts)
        implementation(androidx.paging.runtime)
        implementation(androidx.paging.compose)
    }
}

android {
    namespace = "com.example.feature_note_list"
}