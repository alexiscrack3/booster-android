package com.alexiscrack3.booster.home

import androidx.fragment.app.testing.launchFragmentInContainer
import com.alexiscrack3.booster.BoosterTest
import kotlinx.android.synthetic.main.fragment_home.*
import org.junit.Test

class HomeFragmentTest : BoosterTest() {

    @Test
    fun `entries button emits vocabulary navigation event`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            fragment.home_entries_button.performClick()

//            verify(router).navigate(BoosterNavigationEvent.Vocabulary)
        }
    }

    @Test
    fun `settings button emits settings navigation event`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            fragment.home_settings_button.performClick()

//            verify(router).navigate(BoosterNavigationEvent.Play)
        }
    }
}
