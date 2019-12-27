package com.alexiscrack3.booster

import android.app.Application
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.robolectric.annotation.Config

@Config(
    application = TestApplication::class,
    manifest = Config.NONE,
    sdk = [Build.VERSION_CODES.P]
)
@RunWith(AndroidJUnit4::class)
abstract class BoosterTest : AutoCloseKoinTest() {
    protected val context: Application by lazy { ApplicationProvider.getApplicationContext<Application>() }

    @Before
    open fun setUp() {
        BoosterModule.init(context)
    }

    fun inflateView(@LayoutRes layoutId: Int, enclosingLayoutFactory: (Context) -> ViewGroup = { LinearLayout(it) }, attachToRoot: Boolean = false): View {
        return LayoutInflater.from(context).inflate(layoutId, enclosingLayoutFactory(context), attachToRoot)
    }
}
