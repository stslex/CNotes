plugins {
    id("cnotes.android.library")
    kotlin("kapt")
}

android {
    namespace = "com.stslex.core_model"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    with(libs) {
        implementation(koin.android)
        implementation(androidx.core.kts)

        annotationProcessor(androidx.room.compiler)
        kapt(androidx.room.compiler)

        api(androidx.room.ktx)
        api(androidx.room.paging)
    }
}