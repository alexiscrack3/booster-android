package com.alexiscrack3.booster

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.util.DisplayMetrics
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class BottomDividerItemDecoration(
    private val context: Context,
    private val leftMargin: Int = 0,
    private val rightMargin: Int = 0
) : RecyclerView.ItemDecoration() {
    private var divider: Drawable? = ContextCompat.getDrawable(context, R.drawable.divider_horizontal)

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        c.save()
        val left = parent.paddingLeft + convertDpToPixel(leftMargin)
        val right = parent.width - parent.paddingRight - convertDpToPixel(rightMargin)

        val childCount = parent.childCount
        for (index in 0 until childCount) {
            val child = parent.getChildAt(index)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + (divider?.intrinsicHeight ?: 0)

            divider?.setBounds(left, top, right, bottom)
            divider?.draw(c)
        }
        c.restore()
    }

    private fun convertDpToPixel(dp: Int): Int {
        val displayMetrics = context.resources.displayMetrics
        return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
    }
}
