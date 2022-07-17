plugins {
    `kotlin-dsl`
}

group = "com.stslex.cnotes.buildlogic"

dependencies {
    implementation(libs.android.gradlePlugin)
    implementation(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "cnotes.android.application.compose"
            implementationClass = "AndroidApplicationComposePlugin"
        }
        register("androidApplication") {
            id = "cnotes.android.application"
            implementationClass = "AndroidApplicationPlugin"
        }
        register("androidLibraryCompose") {
            id = "cnotes.android.library.compose"
            implementationClass = "AndroidLibraryComposePlugin"
        }
        register("androidLibrary") {
            id = "cnotes.android.library"
            implementationClass = "AndroidLibraryPlugin"
        }
        register("androidTest") {
            id = "cnotes.android.test"
            implementationClass = "AndroidTestPlugin"
        }
        register("androidLibraryJacoco") {
            id = "cnotes.android.library.jacoco"
            implementationClass = "AndroidLibraryJacocoPlugin"
        }
        register("androidApplicationJacoco") {
            id = "cnotes.android.application.jacoco"
            implementationClass = "AndroidApplicationJacocoPlugin"
        }
    }
}