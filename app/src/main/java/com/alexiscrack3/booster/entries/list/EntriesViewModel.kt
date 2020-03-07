package com.alexiscrack3.booster.entries.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexiscrack3.booster.BoosterViewModel
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.models.Entry
import timber.log.Timber

class EntriesViewModel(
    private val entriesRepository: EntriesRepository
) : BoosterViewModel() {
    private val _entriesData = MutableLiveData<List<Entry>>()

    val entriesData: LiveData<List<Entry>>
        get() = _entriesData

    init {
        getEntries()
    }

    private fun getEntries() {
        entriesRepository.getEntries().subscribe({ entries ->
            _entriesData.postValue(entries)
        }, {
            Timber.e(it)
        }).autoDispose()
    }
}
