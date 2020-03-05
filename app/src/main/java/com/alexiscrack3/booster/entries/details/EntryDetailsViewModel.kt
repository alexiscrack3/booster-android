package com.alexiscrack3.booster.entries.details

import com.alexiscrack3.booster.StateViewModel
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.entries.details.EntryDetailsViewModel.EntryDetailsViewState
import timber.log.Timber

class EntryDetailsViewModel(
    private val entriesRepository: EntriesRepository
) : StateViewModel() {//<EntryDetailsViewState>(EntryDetailsViewState()) {

    fun getEntryDetails(id: String) {
//        entriesRepository.getEntry(id)
//            .doOnSubscribe {
//                updateState {
//                    it.copy(
//                        showProgress = true
//                    )
//                }
//            }
//            .doAfterTerminate {
//                updateState {
//                    it.copy(
//                        showProgress = false
//                    )
//                }
//            }
//            .subscribe({ entry ->
//                updateState {
//                    it.copy(
//                        entry = entry
//                    )
//                }
//            }, {
//                Timber.e(it)
//                updateState {
//                    it.copy(
//                        entry = null
//                    )
//                }
//            }).autoDispose()
    }

    data class EntryDetailsViewState(
        val showProgress: Boolean = false,
        val entry: Entry? = null
    )
}
