package com.stslex.cnotes

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import org.jetbrains.kotlin.konan.properties.Properties

@Suppress("UnstableApiUsage")
fun Project.configureKotlinAndroid(
    commonExtension: CommonExtension<*, *, *, *>,
) {
    commonExtension.apply {
        compileSdk = 32

        defaultConfig {
            minSdk = 23
            val properties = Properties()
            properties.load(project.rootProject.file("local.properties").inputStream())
            buildConfigField(
                "String",
                "FIREBASE_PROJECT_ID",
                properties.getProperty("FIREBASE_PROJECT_ID")
            )
            buildConfigField(
                "String",
                "FIREBASE_APPLICATION_ID",
                properties.getProperty("FIREBASE_APPLICATION_ID")
            )
            buildConfigField(
                "String",
                "FIREBASE_CLIENT_ID",
                properties.getProperty("FIREBASE_CLIENT_ID")
            )
            buildConfigField(
                "String",
                "FIREBASE_API_KEY",
                properties.getProperty("FIREBASE_API_KEY")
            )
            buildConfigField(
                "String",
                "FIREBASE_DATABASE_URL",
                properties.getProperty("FIREBASE_DATABASE_URL")
            )
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            isCoreLibraryDesugaringEnabled = true
        }
        sourceSets.all {
            java.srcDirs("build/generated/ksp/main/kotlin")
        }
        kotlinOptions {
            // Treat all Kotlin warnings as errors (disabled by default)
            // allWarningsAsErrors = properties["warningsAsErrors"] as? Boolean ?: false

            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                // Enable experimental coroutines APIs, including Flow
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlin.Experimental",
                // Enable experimental kotlinx serialization APIs
                "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )

            // Set JVM target to 1.8
            jvmTarget = JavaVersion.VERSION_1_8.toString()

            sourceSets.all {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }
    }

    val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")

    dependencies {
        add("coreLibraryDesugaring", libs.findLibrary("android.desugarJdkLibs").get())
    }
}

private fun CommonExtension<*, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}