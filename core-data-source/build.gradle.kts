plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
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
        implementation(koin.core)
        implementation(koin.android)
        implementation(androidx.core.kts)
        annotationProcessor(androidx.room.compiler)
        kapt(androidx.room.compiler)
        api(androidx.room.runtime)
    }
}