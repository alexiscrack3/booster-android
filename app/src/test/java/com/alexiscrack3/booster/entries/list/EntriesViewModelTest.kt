package com.alexiscrack3.booster.entries.list

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexiscrack3.booster.api.Resource
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
    fun `successful resource with entries is emitted`() {
        val entries = listOf<Entry>(mock())
        val resource: Resource<List<Entry>> = Resource.Success(entries)
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntries() } doReturn Single.just(entries)
        }
        val testObject = EntriesViewModel(entriesRepository)

        val actual = testObject.entriesLiveData.value
        assertThat(actual, equalTo(resource))
    }

    @Test
    fun `failure resource with error is emitted`() {
        val throwable = Throwable()
        val resource: Resource<List<Entry>> = Resource.Failure(throwable)
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntries() } doReturn Single.error(throwable)
        }
        val testObject = EntriesViewModel(entriesRepository)

        val actual = testObject.entriesLiveData.value
        assertThat(actual, equalTo(resource))
    }

    @Test
    fun `loading resource is emitted`() {
        val resource: Resource<List<Entry>> = Resource.Loading()
        val entriesRepository = mock<EntriesRepository> {
            on { this.getEntries() } doReturn Single.never()
        }
        val testObject = EntriesViewModel(entriesRepository)

        val actual = testObject.entriesLiveData.value
        assertThat(actual, equalTo(resource))
    }
}
