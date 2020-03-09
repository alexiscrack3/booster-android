package com.alexiscrack3.booster.entries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.databinding.EntriesFragmentBinding
import com.alexiscrack3.booster.entries.details.EntryDetailsFragment
import com.alexiscrack3.booster.models.Entry
import kotlinx.android.synthetic.main.fragment_entries.*
import org.koin.android.viewmodel.ext.android.viewModel

class EntriesFragment : BoosterFragment() {
    private val entriesViewModel by viewModel<EntriesViewModel>()
    private val entriesAdapter = EntriesAdapter {
        val bundle = bundleOf(
            EntryDetailsFragment.ENTRY_ID_KEY to it.id
        )
        view?.findNavController()?.navigate(R.id.action_entriesFragment_to_entryDetailsFragment, bundle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entriesObserver = Observer<List<Entry>> { entries ->
            entriesAdapter.swap(entries)
        }
        entriesViewModel.entriesLiveData.observe(this, entriesObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<EntriesFragmentBinding>(
            inflater,
            R.layout.fragment_entries,
            container,
            false
        )
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val title = this.getString(R.string.entries)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = title
        entry_list.adapter = entriesAdapter
//        entry_list.addItemDecoration(BottomDividerItemDecoration(requireContext()))
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(), OrientationHelper.VERTICAL
        )
        entry_list.addItemDecoration(dividerItemDecoration)
    }
}
