plugins {
    id("com.android.application") version "7.1.2" apply false
    id("com.android.library") version "7.1.2" apply false
    id("org.jetbrains.kotlin.android") version "1.5.21" apply false
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.serialization") version "1.5.21"
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.38.1")
    }
}

tasks.register(name = "type", type = Delete::class){
    delete(rootProject.buildDir)
}