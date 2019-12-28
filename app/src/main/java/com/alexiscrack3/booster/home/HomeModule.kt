package com.alexiscrack3.booster.home

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(router = get())
    }
}
