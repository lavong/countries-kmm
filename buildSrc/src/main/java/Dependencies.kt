object Versions {
    const val versionCode = 1
    const val versionName = "0.1.0"
    const val compileSdk = 33
    const val minSdk = 28
    const val targetSdk = 33

    const val agp = "8.1.0-rc01"
    const val androidxViewModel = "2.6.1"
    const val apollo = "3.8.2"
    const val coil = "2.4.0"
    const val composeBom = "2023.06.01"
    const val composeCompiler = "1.4.7"
    const val composeNavigation = "2.6.0"
    const val kmmViewModel = "1.0.0-ALPHA-9"
    const val kmpNativeCoroutines = "1.0.0-ALPHA-10"
    const val koin = "3.4.1"
    const val koinCompose = "3.4.5"
    const val ksp = "1.8.21-1.0.11"
    const val kotlin = "1.8.21"
    const val timber = "5.0.1"
}

object Compose {
    const val bom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
    const val koin = "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}"
}

object Koin {
    const val core = "io.insert-koin:koin-core:${Versions.koin}"
    const val test = "io.insert-koin:koin-test:${Versions.koin}"
    const val android = "io.insert-koin:koin-android:${Versions.koin}"
}
