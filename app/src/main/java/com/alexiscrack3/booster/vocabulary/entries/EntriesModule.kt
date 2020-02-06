package com.alexiscrack3.booster.vocabulary.entries

import com.alexiscrack3.booster.vocabulary.EntriesRepository
import com.alexiscrack3.booster.vocabulary.EntriesService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entriesModule = module {
    factory { EntriesService(firestore = get()) }
    factory { EntriesRepository(entriesService = get()) }
    viewModel {
        EntriesViewModel(entriesRepository = get())
    }
}
