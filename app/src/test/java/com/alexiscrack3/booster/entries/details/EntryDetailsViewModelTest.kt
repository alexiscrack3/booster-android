package com.alexiscrack3.booster.entries.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.entries.details.EntryDetailsViewModel.Companion.ENTRY_ID_KEY
import com.alexiscrack3.booster.models.Entry
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Maybe
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class EntryDetailsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `headword is emitted when getting entry by id`() {
        val id = "1"
        val headword = "headword"
        val entry = mock<Entry> {
            on { this.headword } doReturn headword
        }
        val state = mock<SavedStateHandle> {
            on { this.get<String>(ENTRY_ID_KEY) } doReturn id
        }
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntry(id) } doReturn Maybe.just(entry)
        }
        val testObject = EntryDetailsViewModel(state, entriesRepository)

        testObject.getEntryDetails(id)

        testObject.headwordLiveData.observeForever {
            assertThat(it, equalTo(headword))
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun `IllegalArgumentException is thrown when entry by id does not exist in state`() {
        val id = "1"
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntry(id) } doReturn Maybe.never()
        }
        val testObject = EntryDetailsViewModel(mock(), entriesRepository)

        testObject.getEntryDetails(id)
    }
}
