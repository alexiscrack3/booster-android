package com.alexiscrack3.booster.vocabulary.entries

import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.models.Entry
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.android.synthetic.main.item_entry.view.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class EntryViewHolderTest : BoosterTest() {
    private val view = inflateView(R.layout.item_entry)

    @Test
    fun `when bound name text is set`() {
        val entry = Entry(
            "id",
            "headword",
            "class",
            "prounciation",
            emptyList()
        )
        val testObject = EntryViewHolder(view)

        testObject.bind(entry, {})

        assertThat(view.entry_headword_text_view.text.toString(), equalTo(entry.headword))
    }

    @Test
    fun `when bound click event is set`() {
        val entry = Entry(
            "id",
            "headword",
            "class",
            "prounciation",
            emptyList()
        )
        val testObject = EntryViewHolder(view)
        val onClick = mock<OnClickHandler>()
        testObject.bind(entry, onClick)

        testObject.itemView.performClick()

        verify(onClick).invoke(entry)
    }

    private interface OnClickHandler: (Entry) -> Unit
}
