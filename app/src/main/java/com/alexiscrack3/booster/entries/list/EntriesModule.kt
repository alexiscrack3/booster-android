package com.alexiscrack3.booster.entries.list

import com.alexiscrack3.booster.entries.EntriesRepository
import com.alexiscrack3.booster.entries.EntriesService
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val entriesModule = module {
    factory { EntriesService(firestore = get()) }
    factory { EntriesRepository(entriesService = get()) }
    viewModel {
        EntriesViewModel(entriesRepository = get())
    }
}
