package com.alexiscrack3.booster.vocabulary.entries

import android.widget.LinearLayout
import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.models.Entry
import com.nhaarman.mockitokotlin2.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.instanceOf
import org.junit.Assert.assertThat
import org.junit.Test

class EntriesAdapterTest : BoosterTest() {

    @Test
    fun `count is initially 0`() {
        assertThat(EntriesAdapter().itemCount, equalTo(0))
    }

    @Test
    fun `onCreateViewHolder returns view holder`() {
        val entry = mock<Entry>()
        val testObject = EntriesAdapter(listOf(entry))

        val viewHolder = testObject.onCreateViewHolder(LinearLayout(context), 0)

        assertThat(viewHolder, instanceOf(EntryViewHolder::class.java))
    }

    @Test
    fun `onBindViewHolder binds correct item`() {
        val item = mock<Entry>()
        val items = listOf(item)
        val viewHolder = mock<EntryViewHolder>()
        val testObject = EntriesAdapter()
        testObject.swap(items)

        testObject.onBindViewHolder(viewHolder, 0)

        verify(viewHolder).bind(eq(item), any())
    }

    @Test
    fun `swap updates data in adapter`() {
        val items = listOf<Entry>(
            mock(),
            mock(),
            mock()
        )
        val testObject = EntriesAdapter()

        testObject.swap(items)

        assertThat(testObject.itemCount, equalTo(items.size))
    }
}
