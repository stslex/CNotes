plugins {
    id("cnotes.android.library")
    id("cnotes.android.library.compose")
}

dependencies {
    implementation(project(":core-test"))
    implementation(project(":core"))

    with(libs) {
        implementation(androidx.core.kts)

        api(androidx.compose.material3.windowSizeClass)
        api(androidx.compose.ui.core)
        api(androidx.compose.material3)
        api(androidx.compose.ui.tooling.preview)
        api(androidx.lifecycle.runtime)
        api(androidx.activity.compose)
        api(androidx.compose.constraintlayout)
        debugApi(androidx.compose.ui.tooling)

        implementation(androidx.paging.runtime)
    }
}

android {
    namespace = "com.stslex.core_ui"
}