package com.saantiaguilera.greener.view

import android.content.Context
import android.view.MotionEvent
import android.widget.FrameLayout
import android.view.ViewGroup
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.SupportMapFragment

/**
 * TODO Describe what this class do.
 */
class GreenerMapFragment : SupportMapFragment() {

    override fun onCreateView(layoutInflater: LayoutInflater, viewGroup: ViewGroup?, savedInstance: Bundle?): View? {
        val layout = super.onCreateView(layoutInflater, viewGroup, savedInstance)

        val frameLayout = TouchableWrapper(context!!)

        frameLayout.setBackgroundColor(resources.getColor(android.R.color.transparent))

        (layout as ViewGroup).addView(frameLayout,
                ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))

        return layout
    }

    inner class TouchableWrapper(context: Context) : FrameLayout(context) {

        override fun dispatchTouchEvent(event: MotionEvent): Boolean {
            parent.requestDisallowInterceptTouchEvent(true)
            return super.dispatchTouchEvent(event)
        }

    }

}