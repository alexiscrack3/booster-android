package com.alexiscrack3.booster.databindings

import android.view.View
import com.alexiscrack3.booster.api.Resource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test

class BindingAdaptersKtTest {

    @Test
    fun `visibility is set to gone when resource is success`() {
        val view = mock<View>()
        setVisibilityObserver(view, Resource.Success(0))

        verify(view).visibility = View.GONE
    }

    @Test
    fun `visibility is set to visible when resource is loading`() {
        val view = mock<View>()
        setVisibilityObserver(view, Resource.Loading<Int>())

        verify(view).visibility = View.VISIBLE
    }

    @Test
    fun `visibility is set to visible when resource is failure`() {
        val view = mock<View>()
        setVisibilityObserver(view, Resource.Failure<Any>(
            Throwable()
        ))

        verify(view).visibility = View.VISIBLE
    }
}
