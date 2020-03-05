package com.alexiscrack3.booster.entries

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.entries.details.EntryDetailsFragment
import com.alexiscrack3.booster.entries.list.EntriesFragment
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

        showFragment(EntriesFragment(), false)
    }

    override fun onResume() {
        super.onResume()
        router.eventObservable
            .subscribe({
                navigate(it)
            }, {

            })
            .autoDispose()
    }

    private fun navigate(event: BoosterNavigationEvent) {
        when (event) {
            is BoosterNavigationEvent.EntryDetails -> {
                showFragment(EntryDetailsFragment.newInstance(event.entryId))
            }
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        showFragment(R.id.vocabulary_container, fragment, addToBackStack)
    }
}