package com.stslex.cnotes.di

import androidx.navigation.NavController
import androidx.navigation.NavHostController
import org.koin.core.annotation.Single
import org.koin.core.module.Module
import org.koin.dsl.module

@Single
class ActivityComponent {

    val module: (navController: NavHostController) -> Module
        get() = { navController ->
            module {
                single<NavController> { navController }
            }
        }
}