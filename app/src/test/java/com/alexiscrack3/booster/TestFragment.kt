package com.alexiscrack3.booster

import androidx.fragment.app.Fragment

class TestFragment: Fragment() {

    override fun onStart() {
        super.onStart()
        requireActivity().title = "title"
    }
}
