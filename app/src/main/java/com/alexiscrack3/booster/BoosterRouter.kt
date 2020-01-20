package com.alexiscrack3.booster

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class BoosterRouter {
    private val eventSubject: PublishSubject<BoosterNavigationEvent> = PublishSubject.create()
    val eventObservable: Observable<BoosterNavigationEvent> = eventSubject.hide()

    fun navigate(event: BoosterNavigationEvent) {
        eventSubject.onNext(event)
    }
}

sealed class BoosterNavigationEvent {
    object Play : BoosterNavigationEvent()
    object Vocabulary : BoosterNavigationEvent()
    class EntryDetails(val entryId: String) : BoosterNavigationEvent()
}
