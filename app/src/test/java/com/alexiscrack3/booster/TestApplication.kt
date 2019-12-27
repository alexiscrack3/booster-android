package com.alexiscrack3.booster

import android.app.Application

class TestApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setTheme(R.style.AppTheme)
    }
}
