plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.stslex.core_remote_data_source"

    ksp {
        arg("room.schemaLocation", "$projectDir/schemas")
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))
    implementation(project(":core-model"))
    implementation(project(":core-firebase"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.core.kts)
        annotationProcessor(androidx.room.compiler)
        ksp(androidx.room.compiler)
        api(androidx.room.runtime)
    }
}