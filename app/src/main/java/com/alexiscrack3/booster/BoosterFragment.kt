package com.alexiscrack3.booster

import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BoosterFragment : Fragment() {
    private val disposables = CompositeDisposable()

    fun Disposable.autoDispose() {
        disposables.add(this)
    }
}
