package com.alexiscrack3.booster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

abstract class BoosterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.addOnBackStackChangedListener {
            shouldDisplayHomeUp()
        }
        shouldDisplayHomeUp()
    }

    protected fun showFragment(layoutId: Int, fragment: Fragment, addToBackStack: Boolean = true) {
        val fragmentManager = this.supportFragmentManager
        val tag = fragment::class.java.name
        val fragmentToAdd = fragmentManager.findFragmentByTag(tag) ?: fragment
        fragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.animator.fade_in,
                android.R.animator.fade_out,
                android.R.animator.fade_in,
                android.R.animator.fade_out
            )
            .replace(layoutId, fragmentToAdd, tag)
            .apply {
                if (addToBackStack) {
                    addToBackStack(null)
                }
            }
            .commit()
    }

    override fun onSupportNavigateUp(): Boolean {
        supportFragmentManager.popBackStack()
        return true
    }

    private fun shouldDisplayHomeUp() {
        val canGoBack = supportFragmentManager.backStackEntryCount > 0
        supportActionBar?.setDisplayHomeAsUpEnabled(canGoBack)
    }
}
