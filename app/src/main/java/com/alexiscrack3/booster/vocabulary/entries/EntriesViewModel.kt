package com.alexiscrack3.booster.vocabulary.entries

import com.alexiscrack3.booster.StateViewModel
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.vocabulary.EntriesRepository
import com.alexiscrack3.booster.vocabulary.entries.EntriesViewModel.EntriesViewState
import timber.log.Timber

class EntriesViewModel(
    private val entriesRepository: EntriesRepository
) : StateViewModel<EntriesViewState>(EntriesViewState()) {

    init {
        getEntries()
    }

    private fun getEntries() {
        entriesRepository.getEntries()
            .subscribe({ entries ->
                updateState {
                    it.copy(
                        entries = entries
                    )
                }
            }, {
                Timber.e(it)
                updateState {
                    it.copy(
                        entriesError = true
                    )
                }
            }).autoDispose()
    }

    data class EntriesViewState(
        val entries: List<Entry> = emptyList(),
        val entriesError: Boolean = false
    )
}