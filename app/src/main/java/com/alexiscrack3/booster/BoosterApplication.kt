package com.alexiscrack3.booster

import androidx.multidex.MultiDexApplication
import timber.log.Timber

class BoosterApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        BoosterModule.init(this)
    }
}
