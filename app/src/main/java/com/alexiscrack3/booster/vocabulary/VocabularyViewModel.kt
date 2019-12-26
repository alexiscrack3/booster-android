package com.alexiscrack3.booster.vocabulary

import com.alexiscrack3.booster.StateViewModel
import com.alexiscrack3.booster.models.Entry
import com.alexiscrack3.booster.vocabulary.VocabularyViewModel.VocabularyViewState
import timber.log.Timber

class VocabularyViewModel(
    private val vocabularyRepository: VocabularyRepository
) : StateViewModel<VocabularyViewState>(VocabularyViewState()) {

    init {
        getEntries()
    }

    private fun getEntries() {
        vocabularyRepository.getEntries()
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

    data class VocabularyViewState(
        val entries: List<Entry> = emptyList(),
        val entriesError: Boolean = false
    )
}
