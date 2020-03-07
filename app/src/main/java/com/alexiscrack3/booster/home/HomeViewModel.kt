package com.alexiscrack3.booster.home

import android.view.View
import androidx.navigation.findNavController
import com.alexiscrack3.booster.BoosterViewModel
import com.alexiscrack3.booster.R

class HomeViewModel : BoosterViewModel() {

    fun onEntriesClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_vocabularyActivity)
    }

    fun onPlayClick(view: View) {
        view.findNavController().navigate(R.id.action_homeFragment_to_playFragment)
    }
}
