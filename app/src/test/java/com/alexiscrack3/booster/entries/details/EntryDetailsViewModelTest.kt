package com.alexiscrack3.booster.entries.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexiscrack3.booster.entries.EntriesRepository
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
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntry(id) } doReturn Maybe.just(entry)
        }
        val testObject = EntryDetailsViewModel(entriesRepository)

        testObject.getEntryDetails(id)

        testObject.headwordLiveData.observeForever {
            assertThat(it, equalTo(headword))
        }
    }
}
