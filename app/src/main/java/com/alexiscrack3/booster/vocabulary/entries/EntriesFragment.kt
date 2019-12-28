package com.alexiscrack3.booster.vocabulary.entries

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.OrientationHelper
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import kotlinx.android.synthetic.main.fragment_entries.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class EntriesFragment : BoosterFragment() {
    private val entriesViewModel by viewModel<EntriesViewModel>()
    private val router by inject<BoosterRouter>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entries, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as? AppCompatActivity)?.supportActionBar?.title = this.getString(R.string.entries)
        vocabulary_list.adapter = EntriesAdapter(onClick = {
            router.navigate(BoosterNavigationEvent.ENTRY_DETAILS(it.id))
        })
//        vocabulary_list.addItemDecoration(BottomItemDecoration(requireContext()))
        vocabulary_list.addItemDecoration(DividerItemDecoration(requireContext(), OrientationHelper.VERTICAL))
    }

    override fun onResume() {
        super.onResume()
        entriesViewModel.state
            .map { it.entries }
            .subscribe({ entries ->
                val vocabularyAdapter = vocabulary_list.adapter as EntriesAdapter
                vocabularyAdapter.swap(entries)
                Timber.d("Got entries")
            }, {
                Timber.e(it)
            })
            .autoDispose()
    }
}
