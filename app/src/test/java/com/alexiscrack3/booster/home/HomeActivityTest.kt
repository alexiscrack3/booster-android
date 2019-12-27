package com.alexiscrack3.booster.home

import android.os.Build.VERSION_CODES.P
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(
//    application = TestApplication::class,
    manifest = Config.NONE,
    sdk = [P]
)
@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    @Test
    fun `shows home screen`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            val fragment = activity.supportFragmentManager.fragments.first()
            assertThat(fragment, instanceOf(HomeFragment::class.java))
        }.close()
    }
}
