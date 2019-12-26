package com.alexiscrack3.booster.vocabulary

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.models.Entry

class EntryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val headwordTextView = view.findViewById<TextView>(R.id.entry_headword_text_view)

    fun bind(entry: Entry) {
        headwordTextView.text = entry.headword
    }
}
