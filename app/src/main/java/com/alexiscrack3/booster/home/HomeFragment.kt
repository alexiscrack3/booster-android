package com.alexiscrack3.booster.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.databinding.HomeFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BoosterFragment() {
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<HomeFragmentBinding>(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        binding.viewModel = homeViewModel
        return binding.root
    }
}
