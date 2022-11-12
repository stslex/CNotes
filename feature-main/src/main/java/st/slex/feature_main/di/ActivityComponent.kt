package st.slex.feature_main.di

fun interface ActivityComponent {
    operator fun invoke(dependencies: ActivityDependencies)
}