package com.alexiscrack3.booster.home

import android.view.View
import androidx.navigation.NavController
import com.alexiscrack3.booster.R
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

class HomeViewModelTest {

    @Test
    fun `onEntriesClick emits vocabulary event`() {
        val navController = mock<NavController>()
        val view = mock<View>()
        whenever(view.getTag(androidx.navigation.R.id.nav_controller_view_tag)).thenReturn(navController)
//        whenever(view.findNavController()).thenReturn(navController)

        val testObject = HomeViewModel()
        testObject.onEntriesClick(view)

        verify(navController).navigate(R.id.action_homeFragment_to_vocabulary_nav_graph)
    }

    @Test
    fun `onPlayClick emits play event`() {
        val navController = mock<NavController>()
        val view = mock<View>()
        whenever(view.getTag(androidx.navigation.R.id.nav_controller_view_tag)).thenReturn(navController)
//        whenever(view.findNavController()).thenReturn(navController)

        val testObject = HomeViewModel()
        testObject.onPlayClick(view)

        verify(navController).navigate(R.id.action_homeFragment_to_playFragment)
    }
}
