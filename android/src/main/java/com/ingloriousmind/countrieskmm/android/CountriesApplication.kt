package com.ingloriousmind.countrieskmm.android

import android.app.Application
import timber.log.Timber

class CountriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
