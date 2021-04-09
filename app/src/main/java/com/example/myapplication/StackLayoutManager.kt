package com.example.myapplication

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paypal.dashboard.dpToPx

class StackLayoutManager(context: Context?) : LinearLayoutManager(context) {

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        if (state != null) {
            if (recycler != null) {
                detachAndScrapAttachedViews(recycler)
            }
            for (i in 0 until state.itemCount) {
                val child = recycler?.getViewForPosition(i)
                child?.let {
                    addView(child)
                    measureChildWithMargins(child, 0, 0)
                    val mChildWidth = getDecoratedMeasuredWidth(child)
                    val mChildHeight = getDecoratedMeasuredHeight(child)
                    val left = child.paddingLeft
                    val right = mChildWidth - child.paddingRight

                    /**
                     * This will be added as a PS attribute for stack container.
                     * Once the data will be available , constant would be removed.
                     */
                    val top: Int = i * child.dpToPx(64).toInt()
                    val bottom: Int = top + mChildHeight
                    layoutDecorated(child, left, top, right, bottom)
                }
            }
        }
    }
}
