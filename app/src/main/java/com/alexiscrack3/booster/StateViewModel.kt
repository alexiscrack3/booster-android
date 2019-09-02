package com.alexiscrack3.booster

import androidx.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

abstract class StateViewModel<T>(initialState: T) : ViewModel() {
    private val disposables = CompositeDisposable()
    private val stateSubject = BehaviorSubject.create<T>()
    val state: Observable<T> = stateSubject.hide()

    init {
        stateSubject.onNext(initialState)
    }

    fun Disposable.autoDispose() = disposables.add(this)

    protected fun updateState(stateUpdater: (T) -> T) {
        state
            .firstElement()
            .subscribe({
                val updatedState = stateUpdater(it)
                if (updatedState === it) {
                    throw RuntimeException("Updated state can not be same as old state")
                }
                stateSubject.onNext(updatedState)
            }, {
                Timber.e(it, "Error updating state")
            }).autoDispose()
    }
}
