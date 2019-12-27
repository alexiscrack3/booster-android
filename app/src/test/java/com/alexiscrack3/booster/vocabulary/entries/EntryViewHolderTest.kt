package com.alexiscrack3.booster.vocabulary.entries

import com.alexiscrack3.booster.BoosterTest
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.models.Entry
import kotlinx.android.synthetic.main.item_entry.view.*
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test

class EntryViewHolderTest : BoosterTest() {
    private val view = inflateView(R.layout.item_entry)
    private val testObject = EntryViewHolder(view)

    @Test
    fun `when bound name text is set`() {
        val entry = Entry(
            "id",
            "headword",
            "class",
            "prounciation",
            emptyList()
        )
        testObject.bind(entry, {})

        assertThat(view.entry_headword_text_view.text.toString(), equalTo(entry.headword))
    }
}
