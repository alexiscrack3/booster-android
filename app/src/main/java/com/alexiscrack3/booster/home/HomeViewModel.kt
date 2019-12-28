package com.alexiscrack3.booster.home

import androidx.lifecycle.ViewModel
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter

class HomeViewModel(
    private val router: BoosterRouter
) : ViewModel() {

    fun onEntriesClick() {
        router.navigate(BoosterNavigationEvent.VOCABULARY)
    }

    fun onSettingsClick() {
        router.navigate(BoosterNavigationEvent.SETTINGS)
    }
}
