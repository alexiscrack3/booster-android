package com.alexiscrack3.booster.entries.details

import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.entries.details.EntryDetailsFragment.Companion.ENTRY_ID_KEY
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mockito

class EntryDetailsFragmentTest : BoosterTest() {
    private val entryDetailsViewModel by inject<EntryDetailsViewModel>()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    override fun setUp() {
        super.setUp()
        declareMock<EntryDetailsViewModel>()
    }

    @Test
    fun `getEntryDetails is invoked on view momdel`() {
        val id = "1"
        val fragmentScenario = launchFragmentInContainer {
            EntryDetailsFragment().also { fragment ->
                fragment.viewLifecycleOwnerLiveData.observeForever {
                    fragment.arguments = bundleOf(
                        ENTRY_ID_KEY to id
                    )
                }
            }
        }
        fragmentScenario.onFragment {
            verify(entryDetailsViewModel).getEntryDetails(id)
        }
    }
}
