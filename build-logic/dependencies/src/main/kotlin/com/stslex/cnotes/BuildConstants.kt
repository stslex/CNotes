package com.stslex.cnotes

import com.stslex.cnotes.BuildConstants.Options.COROUTINES_FLOW_PREVIEW
import com.stslex.cnotes.BuildConstants.Options.COROUTINE_EXPERIMENTAL
import com.stslex.cnotes.BuildConstants.Options.KOTLIN_EXPERIMENTAL
import com.stslex.cnotes.BuildConstants.Options.REQUIRES_OPT_IN
import com.stslex.cnotes.BuildConstants.Options.SERIALISATION_EXPERIMENTAL

internal object BuildConstants {

    object Paths {
        const val LOCAL_PROPERTIES = "local.properties"
        const val SOURCE_DIR = "build/generated/ksp/main/kotlin"
    }

    object Types {
        const val STRING = "String"
    }

    object Values {
        const val FIREBASE_PROJECT_ID = "FIREBASE_PROJECT_ID"
        const val FIREBASE_APPLICATION_ID = "FIREBASE_APPLICATION_ID"
        const val FIREBASE_CLIENT_ID = "FIREBASE_CLIENT_ID"
        const val FIREBASE_API_KEY = "FIREBASE_API_KEY"
        const val FIREBASE_DATABASE_URL = "FIREBASE_DATABASE_URL"

    }

    object Options {
        const val REQUIRES_OPT_IN = "-Xopt-in=kotlin.RequiresOptIn"
        const val COROUTINE_EXPERIMENTAL = "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
        const val COROUTINES_FLOW_PREVIEW = "-Xopt-in=kotlinx.coroutines.FlowPreview"
        const val KOTLIN_EXPERIMENTAL = "-Xopt-in=kotlin.Experimental"
        const val SERIALISATION_EXPERIMENTAL =
            "-Xopt-in=kotlinx.serialization.ExperimentalSerializationApi"
    }

    object OptionFields {

        @JvmStatic
        val KOTLIN_JVM_OPTIONS = listOf(
            REQUIRES_OPT_IN,
            COROUTINE_EXPERIMENTAL,
            COROUTINES_FLOW_PREVIEW,
            KOTLIN_EXPERIMENTAL,
            SERIALISATION_EXPERIMENTAL
        )
    }
}