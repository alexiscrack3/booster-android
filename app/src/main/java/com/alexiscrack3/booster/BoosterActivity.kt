package com.alexiscrack3.booster

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BoosterActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.addOnBackStackChangedListener {
            shouldDisplayHomeUp()
        }
        shouldDisplayHomeUp()
    }

    override fun onPause() {
        super.onPause()
        disposables.clear()
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

    fun Disposable.autoDispose() {
        disposables.add(this)
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
