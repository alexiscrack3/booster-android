package com.alexiscrack3.booster.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.android.ext.android.inject

class HomeFragment : BoosterFragment() {
    private val router by inject<BoosterRouter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        home_vocabulary_button.setOnClickListener {
            router.navigate(BoosterNavigationEvent.VOCABULARY)
        }
        home_settings_button.setOnClickListener {
            router.navigate(BoosterNavigationEvent.SETTINGS)
        }
    }
}
