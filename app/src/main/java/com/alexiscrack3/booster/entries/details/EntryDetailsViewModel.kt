package com.alexiscrack3.booster.entries.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.Transformations
import com.alexiscrack3.booster.BoosterViewModel
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.models.Entry
import timber.log.Timber

class EntryDetailsViewModel(
    state: SavedStateHandle,
    private val entriesRepository: EntriesRepository
) : BoosterViewModel() {
    private val _entryData = MutableLiveData<Entry>()

    val headwordLiveData: LiveData<String>
        get() = Transformations.map(_entryData) { it.headword.toLowerCase() }

    val categoryLiveData: LiveData<String>
        get() = Transformations.map(_entryData) { it.category.name.toLowerCase() }

    init {
        val entryId = state.get<String>(ENTRY_ID_KEY) ?: throw IllegalArgumentException("missing entry id")
        getEntryDetails(entryId)
    }

    fun getEntryDetails(id: String) {
        entriesRepository.getEntry(id).subscribe({ entry ->
            _entryData.postValue(entry)
        }, {
            Timber.e(it)
        }).autoDispose()
    }

    companion object {
        const val ENTRY_ID_KEY = "ENTRY_ID"
    }
}
