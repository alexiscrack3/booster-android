package com.alexiscrack3.booster.vocabulary

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val vocabularyModule = module {
    factory { VocabularyRepository(vocabularyService = get()) }
    viewModel {
        VocabularyViewModel(vocabularyRepository = get())
    }
}
