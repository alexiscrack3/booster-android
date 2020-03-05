package com.alexiscrack3.booster.entries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.databinding.EntryDetailsFragmentBinding
import org.koin.android.ext.android.inject
import timber.log.Timber

class EntryDetailsFragment : BoosterFragment() {
    private val entryDetailsViewModel by inject<EntryDetailsViewModel>()
    private lateinit var binding: EntryDetailsFragmentBinding

    companion object {
        const val ENTRY_ID_KEY = "ENTRY_ID"

        fun newInstance(entryId: String): Fragment {
            return EntryDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ENTRY_ID_KEY, entryId)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_entry_details,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = this.getString(R.string.details)
    }

    override fun onResume() {
        super.onResume()
//        arguments?.getString(ENTRY_ID_KEY)?.let { entryId ->
//            entryDetailsViewModel.getEntryDetails(entryId)
//
//            entryDetailsViewModel.state
//                .filter { it.entry != null }
//                .map { it.entry }
//                .subscribe({ entry ->
//                    binding.headword = entry?.headword
//                    Timber.d("Got entry")
//                }, {
//                    Timber.e(it)
//                })
//                .autoDispose()
//        }
    }
}
