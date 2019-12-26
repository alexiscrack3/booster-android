package com.alexiscrack3.booster.vocabulary

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.vocabulary.details.EntryDetailsFragment
import com.alexiscrack3.booster.vocabulary.entries.EntriesFragment
import org.koin.android.ext.android.inject

class VocabularyActivity : BoosterActivity() {
    private val router by inject<BoosterRouter>()

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, VocabularyActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)

        router.eventObservable.subscribe({
            navigate(it)
        }, {

        }).autoDispose()

        showFragment(EntriesFragment(), false)
    }

    private fun navigate(event: BoosterNavigationEvent) {
        when (event) {
            is BoosterNavigationEvent.ENTRY_DETAILS -> {
                showFragment(EntryDetailsFragment.newInstance(event.entryId))
            }
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        showFragment(R.id.vocabulary_container, fragment, addToBackStack)
    }
}
