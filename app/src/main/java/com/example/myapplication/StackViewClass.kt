package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.View
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


object StackViewClass {

    /**
     * createCardStackView is a method to create a list as a recyclerview type.
     * @param overlapHeight indicates the over lap height for card.
     */
    @SuppressLint("Range")
    fun createCardStackView(
        ctx: Context,
        overlapHeight: Float
    ): RecyclerView {

        val list = RecyclerView(ctx)
        list.layoutParams = RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
        list.layoutManager =
            LinearLayoutManager(ctx, LinearLayoutManager.VERTICAL, false)

        val linearLayoutManager = LinearLayoutManager(ctx)
        linearLayoutManager.isAutoMeasureEnabled = false
        list.layoutManager = linearLayoutManager

        val itemDecorator =
            ItemDecoration(overlapHeight.toInt())
        list.addItemDecoration(itemDecorator)

        return list
    }

    /**
     * overlapHeightCalculate calculate the overlap height for card.
     * @param height is the height of the card.
     * @param hideRatio is the aspect ratio of previous card visibility in stack. This is taken from Figma.
     * @param cardAspectRatio is the aspect ratio of the card template as per figma design. With the help of aspect ratio we can calculate the height of card.
     * @return the over lap height of the card.
     */
    fun overlapHeightCalculate(ctx: Context): Double {
        val metrics: DisplayMetrics = ctx.getResources().getDisplayMetrics()
        val densityDpi = (metrics.density * 160f).toInt()
        val aspectRatio: Double = .6297
        val height: Double = densityDpi * aspectRatio
        val hideRatio: Double = 0.2962
        return (216 - (216 * hideRatio))
    }

    class ItemDecoration(
        private val overlapHeight: Int
    ) : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            with(outRect) {
                top = if (parent.getChildAdapterPosition(view) == 0) {
                    0
                } else {
                    overlapHeight
                }
            }
        }
    }
}

