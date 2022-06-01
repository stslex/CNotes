plugins {
    id("cnotes.android.application")
    id("cnotes.android.application.compose")
    id("com.google.devtools.ksp")
}

android {
    defaultConfig {
        applicationId = "com.stslex.cnotes"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "com.stslex.cnotes"
}

dependencies {
    implementation(project(":core"))
    implementation(project(":core-ui"))
    implementation(project(":core-navigation"))
    implementation(project(":core-test"))
    implementation(project(":core-model"))
    implementation(project(":core-data-source"))
    implementation(project(":core-firebase"))
    implementation(project(":core-coroutines"))
    implementation(project(":feature-note-list"))
    implementation(project(":feature-single-note"))
    implementation(project(":feature-todo"))
    implementation(project(":feature-profile"))
    implementation(project(":feature-auth-phonenumber"))
    implementation(project(":feature-auth-code"))

    with(libs) {
        implementation(koin.core)
        implementation(koin.android)
        implementation(koin.androidx.compose)
        implementation(koin.annotations)
        ksp(koin.ksp)
        implementation(androidx.paging.runtime)
        implementation(androidx.paging.compose)
        implementation(androidx.core.kts)
    }
}
