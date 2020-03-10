package com.alexiscrack3.booster.entries.details

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.alexiscrack3.booster.entries.EntriesRepository

class EntryDetailsViewModelFactory(
    private val entriesRepository: EntriesRepository,
    private val owner: SavedStateRegistryOwner,
    private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    override fun <T : ViewModel?> create(
        key: String, modelClass: Class<T>, handle: SavedStateHandle
    ): T {
        // Retrieve saved state
//        val restoredState = owner.savedStateRegistry.consumeRestoredStateForKey(key)

        // Create handle to the saved state above
//        val handle = SavedStateHandle.createHandle(restoredState, defaultArgs)

        return EntryDetailsViewModel(handle, entriesRepository) as T
    }
}
