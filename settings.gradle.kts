enableFeaturePreview("VERSION_CATALOGS")

pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "CNotes"
include(":app")
include(":core")
include(":core-ui")
include(":core-navigation")
include(":feature-note-list")
include(":core-test")
include(":feature-single-note")
include(":core-local-data-source")
include(":feature-todo")
include(":core-model")
include(":core-coroutines")
include(":feature-profile")
include(":core-firebase")
include(":feature-auth-phonenumber")
include(":feature-auth-code")
include(":core-firebase-auth")
include(":core-remote-data-source")
include(":core-resources")
