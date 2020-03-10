package com.alexiscrack3.booster.home

import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.R
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.fragment_home.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

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
    fun `onOptionsItemSelected returns true when settings menu item is selected`() {
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
            val menuItem = mock<MenuItem> {
                on { this.itemId } doReturn R.id.action_homeFragment_to_settings_nav_graph
            }
            val actual = fragment.onOptionsItemSelected(menuItem)

            assertThat(actual, equalTo(true))
        }
    }

    @Test
    fun `onOptionsItemSelected returns false when settings menu item does not match any id`() {
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
            val menuItem = mock<MenuItem> {
                on { this.itemId } doReturn 12345
            }
            val actual = fragment.onOptionsItemSelected(menuItem)

            assertThat(actual, equalTo(false))
        }
    }

    @Test
    fun `navigate to settings screen when clicking on the settings menu item`() {
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
            val menuItem = mock<MenuItem> {
                on { this.itemId } doReturn R.id.action_homeFragment_to_settings_nav_graph
            }
            fragment.onOptionsItemSelected(menuItem)
            assertThat(navController.currentDestination?.id, equalTo(R.id.settingsFragment))
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
}
