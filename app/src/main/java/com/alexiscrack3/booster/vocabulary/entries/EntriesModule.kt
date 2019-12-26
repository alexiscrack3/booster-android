package com.alexiscrack3.booster.vocabulary.entries

import com.alexiscrack3.booster.vocabulary.EntriesRepository
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entriesModule = module {
    factory { EntriesRepository(db = get()) }
    viewModel {
        EntriesViewModel(entriesRepository = get())
    }
}
