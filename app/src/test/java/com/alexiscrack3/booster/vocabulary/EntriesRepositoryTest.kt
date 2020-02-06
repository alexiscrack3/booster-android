package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.models.Entry
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Maybe
import io.reactivex.Single
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString

class EntriesRepositoryTest {

    @Test
    fun `getEntries should return entries`() {
        val result = mock<QuerySnapshot>()
        val entries = listOf<Entry>(
            mock()
        )
        val observable = Single.just(result)
        val entriesService = mock<EntriesService> {
            on { this.getEntries() } doReturn observable
        }
        val entriesMapper = mock<EntriesMapper> {
            on { this.map(result) } doReturn entries
        }
        val testObject = EntriesRepository(entriesService, entriesMapper)

        val testObserver = testObject.getEntries().test()

        testObserver.assertValue(entries)
    }

    @Test
    fun `getEntry should return entry`() {
        val result = mock<DocumentSnapshot>()
        val entries = mock<Entry>()
        val observable = Maybe.just(result)
        val entriesService = mock<EntriesService> {
            on { this.getEntry(anyString()) } doReturn observable
        }
        val entriesMapper = mock<EntriesMapper> {
            on { this.map(result) } doReturn entries
        }
        val testObject = EntriesRepository(entriesService, entriesMapper)

        val testObserver = testObject.getEntry(anyString()).test()

        testObserver.assertValue(entries)
    }
}
