package com.alexiscrack3.booster.entries.details

import androidx.lifecycle.*
import com.alexiscrack3.booster.BoosterViewModel
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.entries.EntriesRepository
import timber.log.Timber

class EntryDetailsViewModel(
    private val state: SavedStateHandle,
    private val entriesRepository: EntriesRepository
) : BoosterViewModel() {
    private val _entryData = MutableLiveData<Entry>()

    val headwordLiveData: LiveData<String>
        get() = Transformations.map(_entryData) { it.headword }

    init {
        val id = getEntryId()
        Timber.tag("TESTING").d("THIS IS ID = $id")
        if (id != null) {
            getEntryDetails(id)
        }
    }

    fun getEntryDetails(id: String) {
        entriesRepository.getEntry(id).subscribe({ entry ->
            _entryData.postValue(entry)
        }, {
            Timber.e(it)
        }).autoDispose()
    }

    fun getEntryId() = state.get<String>(ENTRY_ID_KEY)

    fun setEntryId(entryId: String) {
        state.set(ENTRY_ID_KEY, entryId)
    }

    companion object {
        private const val ENTRY_ID_KEY = "ENTRY_ID"
    }
}
