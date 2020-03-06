package com.alexiscrack3.booster.entries.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.alexiscrack3.booster.StateViewModel
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.entries.EntriesRepository
import timber.log.Timber

class EntryDetailsViewModel(
    private val entriesRepository: EntriesRepository
) : StateViewModel() {
    private val _entryData = MutableLiveData<Entry>()

    val headword: LiveData<String>
        get() = Transformations.map(_entryData) { it.headword }

    fun getEntryDetails(id: String) {
        entriesRepository.getEntry(id).subscribe({ entry ->
            _entryData.postValue(entry)
        }, {
            Timber.e(it)
        }).autoDispose()
    }
}
