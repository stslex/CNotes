package com.stslex.cnotes

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.stslex.cnotes.LocalPropertiesConstants.FIREBASE_API_KEY
import com.stslex.cnotes.LocalPropertiesConstants.FIREBASE_APPLICATION_ID
import com.stslex.cnotes.LocalPropertiesConstants.FIREBASE_CLIENT_ID
import com.stslex.cnotes.LocalPropertiesConstants.FIREBASE_DATABASE_URL
import com.stslex.cnotes.LocalPropertiesConstants.FIREBASE_PROJECT_ID
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
        compileSdk = 33

        defaultConfig {
            minSdk = 26
            Properties().apply {
                load(project.rootProject.file("local.properties").inputStream())
            }.let(::setUpBuildConstants)
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_11
            targetCompatibility = JavaVersion.VERSION_11
            isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + listOf(
                "-Xopt-in=kotlin.RequiresOptIn",
                "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
                "-Xopt-in=kotlinx.coroutines.FlowPreview",
                "-Xopt-in=kotlin.Experimental",
                "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
            )
            jvmTarget = "11"
            sourceSets.all {
                kotlin.srcDir("build/generated/ksp/$name/kotlin")
            }
        }

        packagingOptions {
            resources {
                excludes += "/META-INF/{AL2.0,LGPL2.1}"
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

private fun DefaultConfig.setUpBuildConstants(properties: Properties): Unit = with(properties) {
    buildConfigField("String", FIREBASE_PROJECT_ID, getProperty(FIREBASE_PROJECT_ID))
    buildConfigField("String", FIREBASE_APPLICATION_ID, getProperty(FIREBASE_APPLICATION_ID))
    buildConfigField("String", FIREBASE_CLIENT_ID, getProperty(FIREBASE_CLIENT_ID))
    buildConfigField("String", FIREBASE_API_KEY, getProperty(FIREBASE_API_KEY))
    buildConfigField("String", FIREBASE_DATABASE_URL, getProperty(FIREBASE_DATABASE_URL))
}