package com.ingloriousmind.countrieskmm.android

import android.app.Application
import com.ingloriousmind.countrieskmm.android.di.appModule
import com.ingloriousmind.countrieskmm.di.initKoin
import timber.log.Timber

class CountriesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        initKoin(appModule)
    }
}
