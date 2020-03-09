package com.alexiscrack3.booster.entries.list

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.models.Entry
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import kotlinx.android.synthetic.main.fragment_entries.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class EntriesFragmentTest: BoosterTest() {
    private val mEntriesLiveData = MutableLiveData<List<Entry>>()

    // required to make your Mock via Koin
    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    override fun setUp() {
        super.setUp()
        declareMock<EntriesViewModel> {
            given(this.entriesLiveData).willReturn(mEntriesLiveData)
        }
    }

    @Test
    fun `navigate to entry details screen when clicking on entry`() {
        val navController = TestNavHostController(context).apply {
            setGraph(R.navigation.vocabulary_nav_graph)
        }
        val fragmentScenario = launchFragmentInContainer {
            EntriesFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever {
                    Navigation.setViewNavController(fragment.requireView(), navController)
                }
            }
        }
        fragmentScenario.onFragment { fragment ->
            mEntriesLiveData.value = listOf(mock())
            
            val linearLayoutManager = fragment.entry_list.layoutManager as LinearLayoutManager
            val viewHolder = linearLayoutManager.getChildAt(0)
            viewHolder?.performClick()

            assertThat(navController.currentDestination?.id, equalTo(R.id.entryDetailsFragment))
        }
    }
}
