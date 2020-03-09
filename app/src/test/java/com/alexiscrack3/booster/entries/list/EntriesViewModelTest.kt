package com.alexiscrack3.booster.entries.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.models.Entry
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Single
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Rule
import org.junit.Test

class EntriesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun `entries are emitted`() {
        val entries = listOf<Entry>(mock())
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntries() } doReturn Single.just(entries)
        }
        val testObject = EntriesViewModel(entriesRepository)

        val actual = testObject.entriesLiveData.value
        assertThat(actual, equalTo(entries))
    }
}
