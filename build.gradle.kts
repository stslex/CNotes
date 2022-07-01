plugins {
    id("com.google.devtools.ksp") version "1.7.0-1.0.6"
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.7.0" apply false
    id("com.github.ben-manes.versions") version "0.41.0"
    id("nl.littlerobots.version-catalog-update") version "0.5.1"
}

buildscript {
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath(libs.android.gradlePlugin)
        classpath(libs.kotlin.gradlePlugin)
    }
}

tasks.register(name = "type", type = Delete::class) {
    delete(rootProject.buildDir)
}