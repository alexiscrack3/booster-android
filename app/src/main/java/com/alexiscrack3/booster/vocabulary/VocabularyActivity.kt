package com.alexiscrack3.booster.vocabulary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.R
import org.koin.android.ext.android.inject
import timber.log.Timber

class VocabularyActivity : BoosterActivity() {
    private val vocabularyViewModel by inject<VocabularyViewModel>()

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, VocabularyActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
    }

    override fun onResume() {
        super.onResume()
        vocabularyViewModel.state.map { it.entries }.subscribe({
            Timber.d("Got entries")
        }, {
        }).autoDispose()
    }
}
