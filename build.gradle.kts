plugins {
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
    id("com.android.library") version "7.2.0" apply false
    id("org.jetbrains.kotlin.android") version "1.6.21" apply false
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