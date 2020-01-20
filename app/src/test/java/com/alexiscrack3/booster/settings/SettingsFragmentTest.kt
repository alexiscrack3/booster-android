package com.alexiscrack3.booster.settings

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.preference.PreferenceManager
import com.alexiscrack3.booster.BoosterTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class SettingsFragmentTest : BoosterTest() {

    @Test
    fun `default value of switch is equal to false`() {
        val testObject = launchFragmentInContainer<SettingsFragment>()
        testObject.onFragment {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val actual = sharedPreferences.getBoolean("example_switch", false)
            assertThat(actual, equalTo(false))
        }
    }
}