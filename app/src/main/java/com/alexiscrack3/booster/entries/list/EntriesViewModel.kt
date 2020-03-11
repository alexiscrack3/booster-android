package com.alexiscrack3.booster.entries.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexiscrack3.booster.BoosterViewModel
import com.alexiscrack3.booster.Resource
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.models.Entry

class EntriesViewModel(
    private val entriesRepository: EntriesRepository
) : BoosterViewModel() {
    private val _entriesData = MutableLiveData<Resource<List<Entry>>>()

    val entriesLiveData: LiveData<Resource<List<Entry>>>
        get() = _entriesData

    init {
        getEntries()
    }

    private fun getEntries() {
        entriesRepository.getEntries()
            .doOnSubscribe {
                _entriesData.postValue(Resource.Loading())
            }
            .subscribe({ entries ->
            _entriesData.postValue(Resource.Success(entries))
        }, {
            _entriesData.postValue(Resource.Failure(it))
        }).autoDispose()
    }
}
