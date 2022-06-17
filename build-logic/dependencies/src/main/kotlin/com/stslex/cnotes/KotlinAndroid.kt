package com.stslex.cnotes

import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.DefaultConfig
import com.stslex.cnotes.BuildConstants
import com.stslex.cnotes.BuildConstants.OptionFields.KOTLIN_JVM_OPTIONS
import com.stslex.cnotes.BuildConstants.Paths.LOCAL_PROPERTIES
import com.stslex.cnotes.BuildConstants.Paths.SOURCE_DIR
import com.stslex.cnotes.BuildConstants.Types.STRING
import com.stslex.cnotes.BuildConstants.Values.FIREBASE_API_KEY
import com.stslex.cnotes.BuildConstants.Values.FIREBASE_APPLICATION_ID
import com.stslex.cnotes.BuildConstants.Values.FIREBASE_CLIENT_ID
import com.stslex.cnotes.BuildConstants.Values.FIREBASE_DATABASE_URL
import com.stslex.cnotes.BuildConstants.Values.FIREBASE_PROJECT_ID
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
            minSdk = 26
            Properties().apply {
                load(project.rootProject.file(LOCAL_PROPERTIES).inputStream())
            }.let(::setUpBuildConstants)
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
            isCoreLibraryDesugaringEnabled = true
        }
        sourceSets.all {
            java.srcDirs(SOURCE_DIR)
        }
        kotlinOptions {
            freeCompilerArgs = freeCompilerArgs + KOTLIN_JVM_OPTIONS
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

private fun DefaultConfig.setUpBuildConstants(properties: Properties): Unit = with(properties) {
    buildConfigField(STRING, FIREBASE_PROJECT_ID, getProperty(FIREBASE_PROJECT_ID))
    buildConfigField(STRING, FIREBASE_APPLICATION_ID, getProperty(FIREBASE_APPLICATION_ID))
    buildConfigField(STRING, FIREBASE_CLIENT_ID, getProperty(FIREBASE_CLIENT_ID))
    buildConfigField(STRING, FIREBASE_API_KEY, getProperty(FIREBASE_API_KEY))
    buildConfigField(STRING, FIREBASE_DATABASE_URL, getProperty(FIREBASE_DATABASE_URL))
}