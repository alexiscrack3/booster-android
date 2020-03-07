package com.alexiscrack3.booster.home

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import com.alexiscrack3.booster.*
import com.alexiscrack3.booster.play.PlayFragment
import com.alexiscrack3.booster.entries.VocabularyActivity
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.koin.test.inject

class HomeActivityTest : BoosterTest() {
//    private val router by inject<BoosterRouter>()

    @Test
    fun `title is set on toolbar`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            assertThat(activity.title.toString(), equalTo("Booster"))
        }.close()
    }

    @Test
    fun `title is updated on toolbar`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            val title = "Booster"
            assertThat(activity.title.toString(), equalTo(title))

            activity.supportFragmentManager
                .beginTransaction()
                .replace(R.id.home_container, TestFragment())
                .addToBackStack(null)
                .commit()

            assertThat(activity.title.toString(), not(title))

            activity.supportFragmentManager.popBackStack()

            assertThat(activity.title.toString(), equalTo(title))
        }.close()
    }

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
    fun `shows play screen`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
//            router.navigate(BoosterNavigationEvent.Play)

            val fragment = activity.supportFragmentManager.fragments.first()
            assertThat(fragment, instanceOf(PlayFragment::class.java))
        }.close()
    }

    @Test
    fun `shows vocabulary screen`() {
        Intents.init()

        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity {
//            router.navigate(BoosterNavigationEvent.Vocabulary)

            intended(hasComponent(VocabularyActivity::class.java.name))
        }.close()

        Intents.release()
    }
}
