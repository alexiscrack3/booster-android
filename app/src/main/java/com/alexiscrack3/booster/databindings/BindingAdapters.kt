package com.alexiscrack3.booster.databindings

import android.view.View
import androidx.databinding.BindingAdapter
import com.alexiscrack3.booster.api.Resource

@BindingAdapter("app:visibilityObserver")
fun setVisibilityObserver(view: View, resource: Resource<*>) {
    view.visibility = if (resource is Resource.Success) {
        View.GONE
    } else {
        View.VISIBLE
    }
}
