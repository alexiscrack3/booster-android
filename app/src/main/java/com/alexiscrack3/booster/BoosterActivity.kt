package com.alexiscrack3.booster

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BoosterActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()

    protected fun showFragment(layoutId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        val fragmentManager = this.supportFragmentManager
        val tag = fragment::class.java.name
        val fragmentToAdd = fragmentManager.findFragmentByTag(tag) ?: fragment
        fragmentManager
            .beginTransaction()
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
}
