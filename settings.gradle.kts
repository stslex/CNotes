pluginManagement {
    @Suppress("UnstableApiUsage")
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    @Suppress("UnstableApiUsage")
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
include(":core-data-source")
include(":feature-todo")
include(":core-model")
