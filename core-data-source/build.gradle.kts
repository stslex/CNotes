plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.stslex.core_data_source"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-model"))

    with(libs) {
        implementation(androidx.core.kts)
        implementation(hilt.android.core)
        kapt(hilt.android.compiler)

        annotationProcessor(androidx.room.compiler)
        kapt(androidx.room.compiler)

        api(androidx.room.runtime)
    }
}