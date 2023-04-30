plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("com.apollographql.apollo3") version Versions.apollo
    id("com.google.devtools.ksp") version Versions.ksp
    id("com.rickclephas.kmp.nativecoroutines") version Versions.kmpNativeCoroutines
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "common"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("com.apollographql.apollo3:apollo-runtime:${Versions.apollo}")
                api("com.rickclephas.kmm:kmm-viewmodel-core:${Versions.kmmViewModel}")
                implementation(Koin.core)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Koin.test)
            }
        }
        val androidMain by getting {
            dependencies {
                api("com.jakewharton.timber:timber:${Versions.timber}")
                api("androidx.lifecycle:lifecycle-viewmodel:${Versions.androidxViewModel}")
                api("androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxViewModel}")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }

        all {
            languageSettings.optIn("kotlin.RequiresOptIn")
            languageSettings.optIn("kotlin.experimental.ExperimentalObjCName")
        }
    }
}

android {
    namespace = "com.ingloriousmind.countrieskmm"
    compileSdk = Versions.compileSdk
    defaultConfig {
        minSdk = Versions.minSdk
    }
}

apollo {
    service("CountriesService") {
        packageName.set("com.ingloriousmind.countrieskmm")
    }
}
