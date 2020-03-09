package com.alexiscrack3.booster.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.settings.SettingsActivity
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.fragment_home.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.robolectric.Shadows.shadowOf

class HomeFragmentTest : BoosterTest() {

    @Test
    fun `onCreateOptionsMenu inflates options menu`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            val menu = mock<Menu>()
            val inflater = mock<MenuInflater>()

            fragment.onCreateOptionsMenu(menu, inflater)

            verify(inflater).inflate(R.menu.options, menu)
        }
    }

    @Test
    fun `onOptionsItemSelected returns true when settings item is selected`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            val item = mock<MenuItem> {
                on { this.itemId } doReturn R.id.settings
            }
            val actual = fragment.onOptionsItemSelected(item)

            assertThat(actual, equalTo(true))
        }
    }

    @Test
    fun `onOptionsItemSelected returns false when item does not match any id`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            val item = mock<MenuItem>()
            val actual = fragment.onOptionsItemSelected(item)

            assertThat(actual, equalTo(false))
        }
    }

    @Test
    fun `settings screen is started when settings item is selected`() {
        val fragmentScenario = launchFragmentInContainer<HomeFragment>()
        fragmentScenario.onFragment { fragment ->
            val item = mock<MenuItem> {
                on { this.itemId } doReturn R.id.settings
            }
            fragment.onOptionsItemSelected(item)

            val actual = shadowOf(context).nextStartedActivity.component?.className
            assertThat(actual, equalTo(SettingsActivity::class.java.name))
        }
    }

    @Test
    fun `navigate to play screen when clicking on the play button`() {
        val navController = TestNavHostController(context).apply {
            setGraph(R.navigation.home_nav_graph)
        }

        val fragmentScenario = launchFragmentInContainer {
            HomeFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever {
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }
        fragmentScenario.onFragment { fragment ->
            fragment.home_play_button.performClick()
            assertThat(navController.currentDestination?.id, equalTo(R.id.playFragment))
        }
    }

    @Test
    fun `navigate to entries screen when clicking on the entries button`() {
        val navController = TestNavHostController(context).apply {
            setGraph(R.navigation.home_nav_graph)
        }

        val fragmentScenario = launchFragmentInContainer {
            HomeFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever {
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }
        fragmentScenario.onFragment { fragment ->
            fragment.home_entries_button.performClick()
            assertThat(navController.currentDestination?.id, equalTo(R.id.entriesFragment))
        }
    }
}
