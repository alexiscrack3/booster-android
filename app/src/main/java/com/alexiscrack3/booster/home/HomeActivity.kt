package com.alexiscrack3.booster.home

import android.os.Bundle
import com.alexiscrack3.booster.BoosterActivity
import com.alexiscrack3.booster.R

class HomeActivity : BoosterActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportFragmentManager.addOnBackStackChangedListener {
            updateToolbarTitle()
        }
    }

    private fun updateToolbarTitle() {
        if (supportFragmentManager.backStackEntryCount == 0) {
            this.title = getString(R.string.app_name)
        }
    }
}
