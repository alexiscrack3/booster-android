package com.alexiscrack3.booster.home

import com.alexiscrack3.booster.BoosterNavigationEvent
import com.alexiscrack3.booster.BoosterRouter
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `onEntriesClick emits vocabulary event`() {
        val router = mock<BoosterRouter>()
        val testObject = HomeViewModel(router)

        testObject.onEntriesClick()

        verify(router).navigate(BoosterNavigationEvent.Vocabulary)
    }

    @Test
    fun `onPlayClick emits play event`() {
        val router = mock<BoosterRouter>()
        val testObject = HomeViewModel(router)

        testObject.onPlayClick()

        verify(router).navigate(BoosterNavigationEvent.Play)
    }
}
