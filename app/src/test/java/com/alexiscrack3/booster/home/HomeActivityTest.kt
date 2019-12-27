package com.alexiscrack3.booster.home

import android.app.Application
import android.os.Build.VERSION_CODES.P
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.vocabulary.VocabularyActivity
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.AutoCloseKoinTest
import org.koin.test.inject
import org.robolectric.annotation.Config

@Config(
//    application = TestApplication::class,
    manifest = Config.NONE,
    sdk = [P]
)
@RunWith(AndroidJUnit4::class)
class HomeActivityTest : AutoCloseKoinTest() {
    private val router by inject<BoosterRouter>()
    private val context: Application by lazy { ApplicationProvider.getApplicationContext<Application>() }

    @Test
    fun `home screen should not be added to back stack`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            val fragment = activity.supportFragmentManager.backStackEntryCount
            assertThat(fragment, equalTo(0))
        }.close()
    }

    @Test
    fun `shows home screen`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            val fragment = activity.supportFragmentManager.fragments.first()
            assertThat(fragment, instanceOf(HomeFragment::class.java))
        }.close()
    }

    @Test
    fun `shows settings screen`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            router.navigate(BoosterNavigationEvent.SETTINGS)

            val fragment = activity.supportFragmentManager.fragments.first()
            assertThat(fragment, instanceOf(SettingsFragment::class.java))
        }.close()
    }

    @Test
    fun `shows vocabulary screen`() {
        Intents.init()

        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity {
            router.navigate(BoosterNavigationEvent.VOCABULARY)

            intended(hasComponent(VocabularyActivity::class.java.name))
        }.close()

        Intents.release()
    }
}
