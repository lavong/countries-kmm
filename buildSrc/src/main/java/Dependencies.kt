object Versions {
    const val versionCode = 1
    const val versionName = "0.1.0"
    const val compileSdk = 33
    const val minSdk = 28
    const val targetSdk = 33

    const val agp = "8.1.0-beta01"
    const val coil = "2.1.0"
    const val composeBom = "2023.03.00"
    const val composeCompiler = "1.4.6"
    const val composeNavigation = "2.5.3"
    const val kotlin = "1.8.20"
}

object Compose {
    const val bom = "androidx.compose:compose-bom:${Versions.composeBom}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
}
