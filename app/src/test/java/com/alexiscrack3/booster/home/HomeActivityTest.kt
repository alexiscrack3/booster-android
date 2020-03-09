package com.alexiscrack3.booster.home

import androidx.test.core.app.ActivityScenario
import com.alexiscrack3.booster.BoosterTest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class HomeActivityTest : BoosterTest() {

    @Test
    fun `title is set on toolbar`() {
        val activityScenario = ActivityScenario.launch(HomeActivity::class.java)
        activityScenario.onActivity { activity ->
            assertThat(activity.title.toString(), equalTo("Booster"))
        }.close()
    }
}
