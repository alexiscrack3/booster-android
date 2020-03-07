package com.alexiscrack3.booster

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BoosterViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    protected fun Disposable.autoDispose() = disposables.add(this)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
