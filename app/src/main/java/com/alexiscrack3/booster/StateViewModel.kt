package com.alexiscrack3.booster

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class StateViewModel : ViewModel() {
    private val disposables = CompositeDisposable()

    fun Disposable.autoDispose() = disposables.add(this)

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }
}
