import com.stslex.cnotes.configureKotlinAndroid

plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    configureKotlinAndroid(this)

    defaultConfig {
        @Suppress("UnstableApiUsage")
        targetSdk = 33
    }
}