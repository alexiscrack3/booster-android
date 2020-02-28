package com.alexiscrack3.booster.entries.details

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entryDetailsModule = module {
    viewModel {
        EntryDetailsViewModel(entriesRepository = get())
    }
}
