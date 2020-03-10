package com.alexiscrack3.booster.entries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.databinding.EntryDetailsFragmentBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class EntryDetailsFragment : BoosterFragment() {
    private val entryDetailsViewModel by inject<EntryDetailsViewModel> { parametersOf(this) }
    private lateinit var binding: EntryDetailsFragmentBinding

    companion object {
        const val ENTRY_ID_KEY = "ENTRY_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<EntryDetailsFragmentBinding>(
            inflater,
            R.layout.fragment_entry_details,
            container,
            false
        ).apply {
            lifecycleOwner = this@EntryDetailsFragment
            viewModel = entryDetailsViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = this.getString(R.string.details)

        arguments?.getString(ENTRY_ID_KEY)?.let { entryId ->
            entryDetailsViewModel.setEntryId(entryId)
        }
    }
}
