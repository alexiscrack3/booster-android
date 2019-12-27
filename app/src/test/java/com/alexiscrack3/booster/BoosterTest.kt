package com.alexiscrack3.booster

import android.app.Application
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.annotation.Config

@Config(
//    application = TestApplication::class,
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.P]
)
@RunWith(AndroidJUnit4::class)
abstract class BoosterTest : AutoCloseKoinTest() {
    protected val context: Application by lazy { ApplicationProvider.getApplicationContext<Application>() }
}