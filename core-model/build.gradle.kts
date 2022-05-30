plugins {
    id("cnotes.android.library")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.stslex.core_model"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.core.kts)
        annotationProcessor(androidx.room.compiler)
        ksp(androidx.room.compiler)
        api(androidx.room.ktx)
        api(androidx.room.paging)
    }
}