package com.alexiscrack3.booster.home

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.databinding.HomeFragmentBinding
import com.alexiscrack3.booster.settings.SettingsActivity
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BoosterFragment() {
    private val homeViewModel by viewModel<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

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

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == R.id.settings) {
            val intent = SettingsActivity.getIntent(requireContext())
            startActivity(intent)
            true
        } else {
            false
        }
    }
}
