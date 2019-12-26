package com.alexiscrack3.booster.vocabulary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alexiscrack3.booster.BoosterFragment
import com.alexiscrack3.booster.R
import kotlinx.android.synthetic.main.fragment_vocabulary.*
import org.koin.android.viewmodel.ext.android.viewModel
import timber.log.Timber

class VocabularyFragment : BoosterFragment() {
    private val vocabularyViewModel by viewModel<VocabularyViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_vocabulary, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vocabulary_list.adapter = VocabularyAdapter()
    }

    override fun onResume() {
        super.onResume()
        vocabularyViewModel.state.map { it.entries }.subscribe({ entries ->
            val vocabularyAdapter = vocabulary_list.adapter as VocabularyAdapter
            vocabularyAdapter.swap(entries)
            Timber.d("Got entries")
        }, {
            Timber.e(it)
        }).autoDispose()
    }
}
