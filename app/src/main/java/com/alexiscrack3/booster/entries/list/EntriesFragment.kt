package com.alexiscrack3.booster.entries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.databinding.EntriesFragmentBinding
import com.alexiscrack3.booster.models.Entry
import kotlinx.android.synthetic.main.fragment_entries.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class EntriesFragment : BoosterFragment() {
    private val entriesViewModel by viewModel<EntriesViewModel>()
    private val router by inject<BoosterRouter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val entriesObserver = Observer<List<Entry>> { entries ->
            val vocabularyAdapter = vocabulary_list.adapter as EntriesAdapter
            vocabularyAdapter.swap(entries)
        }
        entriesViewModel.entriesData.observe(this, entriesObserver)
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
        vocabulary_list.adapter = EntriesAdapter {
            router.navigate(BoosterNavigationEvent.EntryDetails(it.id))
        }
//        vocabulary_list.addItemDecoration(BottomDividerItemDecoration(requireContext()))
        val dividerItemDecoration = DividerItemDecoration(
            requireContext(), OrientationHelper.VERTICAL
        )
        vocabulary_list.addItemDecoration(dividerItemDecoration)
    }
}
