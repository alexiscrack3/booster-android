package com.alexiscrack3.booster.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.play.PlayFragment
import com.alexiscrack3.booster.entries.VocabularyActivity
import org.koin.android.ext.android.inject

class HomeActivity : BoosterActivity() {
    private val router by inject<BoosterRouter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.addOnBackStackChangedListener {
            updateToolbarTitle()
        }

        showFragment(HomeFragment(), false)
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
            BoosterNavigationEvent.Play -> showFragment(PlayFragment())
            BoosterNavigationEvent.Vocabulary -> {
                val intent = VocabularyActivity.getIntent(this)
                startActivity(intent)
            }
        }
    }

    private fun showFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        showFragment(R.id.home_container, fragment, addToBackStack)
    }

    private fun updateToolbarTitle() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            this.title = getString(R.string.app_name)
        }
    }
}
