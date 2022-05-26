import com.stslex.cnotes.configureKotlinAndroid

plugins {
    id("com.android.test")
    kotlin("android")
}

android {
    configureKotlinAndroid(this)

    defaultConfig {
        @Suppress("UnstableApiUsage")
        targetSdk = 32
    }
}
