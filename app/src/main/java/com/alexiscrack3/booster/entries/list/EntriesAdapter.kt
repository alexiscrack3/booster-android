package com.alexiscrack3.booster.entries.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.models.Entry

class EntriesAdapter(
    entries: List<Entry> = emptyList(),
    private val onClick: (Entry) -> Unit = {}
) : RecyclerView.Adapter<EntryViewHolder>() {
    private val entries = entries.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_entry, parent, false)
        return EntryViewHolder(view)
    }

    override fun getItemCount() = entries.size

    override fun onBindViewHolder(holder: EntryViewHolder, position: Int) {
        val entry = entries[position]
        holder.bind(entry, onClick)
    }

    fun swap(entries: List<Entry>) {
        this.entries.clear()
        this.entries.addAll(entries)
        notifyDataSetChanged()
    }
}
