package com.alexiscrack3.booster.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.alexiscrack3.booster.R
import com.alexiscrack3.booster.vocabulary.VocabularyActivity
import org.koin.android.ext.android.inject

class HomeActivity : BoosterActivity() {
    private val router by inject<BoosterRouter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        showFragment(R.id.home_frame_layout, HomeFragment())

        router.eventObservable.subscribe({
            navigate(it)
        }, {

        }).autoDispose()

        router.navigate(BoosterNavigationEvent.HOME)
    }

    private fun navigate(event: BoosterNavigationEvent) {
        when (event) {
            BoosterNavigationEvent.HOME -> showFragment(HomeFragment())
            BoosterNavigationEvent.VOCABULARY -> {
                val intent = VocabularyActivity.getIntent(this)
                startActivity(intent)
            }
        }
    }

    private fun showFragment(fragment: Fragment) {
        showFragment(R.id.home_frame_layout, fragment)
    }
}
