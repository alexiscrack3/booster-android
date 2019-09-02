package com.alexiscrack3.booster

import android.app.Application
import timber.log.Timber

class BoosterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
