package com.alexiscrack3.booster.entries.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alexiscrack3.booster.BoosterViewModel
import com.alexiscrack3.booster.api.Resource
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.models.Entry

class EntriesViewModel(
    private val entriesRepository: EntriesRepository
) : BoosterViewModel() {
    private val _entriesLiveData = MutableLiveData<Resource<List<Entry>>>(Resource.Loading())

    val entriesLiveData: LiveData<Resource<List<Entry>>>
        get() = _entriesLiveData

    init {
        getEntries()
    }

    private fun getEntries() {
        entriesRepository.getEntries()
            .doOnSubscribe {
                _entriesLiveData.postValue(Resource.Loading())
            }
            .subscribe({ entries ->
                _entriesLiveData.postValue(Resource.Success(entries))
            }, {
                _entriesLiveData.postValue(Resource.Failure(it))
            })
            .autoDispose()
    }
}
