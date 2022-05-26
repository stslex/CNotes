plugins {
    id("cnotes.android.library")
    id("dagger.hilt.android.plugin")
    kotlin("kapt")
}

android {
    namespace = "com.stslex.core_model"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-test"))

    with(libs) {
        implementation(androidx.core.kts)
        implementation(hilt.android.core)
        kapt(hilt.android.compiler)

        annotationProcessor(androidx.room.compiler)
        kapt(androidx.room.compiler)

        api(androidx.room.ktx)
        api(androidx.room.paging)
    }
}