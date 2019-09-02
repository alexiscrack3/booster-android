package com.alexiscrack3.booster.vocabulary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexiscrack3.booster.R
import org.koin.android.ext.android.inject
import timber.log.Timber

class VocabularyActivity : AppCompatActivity() {
    private val vocabularyViewModel by inject<VocabularyViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
    }

    override fun onResume() {
        super.onResume()
        vocabularyViewModel.state.map { it.entries }.subscribe({
            Timber.d("Got entries")
        }, {
        })
    }
}
