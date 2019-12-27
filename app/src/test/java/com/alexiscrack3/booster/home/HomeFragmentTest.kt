package com.alexiscrack3.booster.home

import androidx.fragment.app.testing.launchFragmentInContainer
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.BoosterTest
import kotlinx.android.synthetic.main.fragment_home.*
import org.junit.Before
import org.junit.Test
import org.koin.test.inject
import org.koin.test.mock.declareMock
import org.mockito.Mockito.verify

class HomeFragmentTest : BoosterTest() {
    private val router by inject<BoosterRouter>()

    @Before
    override fun setUp() {
        super.setUp()
        declareMock<BoosterRouter>()
    }

    @Test
    fun `entries button emits vocabulary navigation event`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            fragment.home_entries_button.performClick()

            verify(router).navigate(BoosterNavigationEvent.VOCABULARY)
        }
    }

    @Test
    fun `settings button emits settings navigation event`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            fragment.home_settings_button.performClick()

            verify(router).navigate(BoosterNavigationEvent.SETTINGS)
        }
    }
}
