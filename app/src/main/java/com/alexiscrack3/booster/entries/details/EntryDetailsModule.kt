package com.alexiscrack3.booster.entries.details

import androidx.savedstate.SavedStateRegistryOwner
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entryDetailsModule = module {
    viewModel { (entryId: String, owner: SavedStateRegistryOwner) ->
        EntryDetailsViewModelFactory(
            entryId = entryId,
            entriesRepository = get(),
            owner = owner
        ).create(EntryDetailsViewModel::class.java)
    }
}
