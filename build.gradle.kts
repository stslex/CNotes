plugins {
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
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