package st.slex.feature_main.di

import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

class ActivityComponentImpl : ActivityComponent {

    override fun invoke(dependencies: ActivityDependencies) {
        loadKoinModules(
            module {
                single { dependencies.navController }
            }
        )
    }
}