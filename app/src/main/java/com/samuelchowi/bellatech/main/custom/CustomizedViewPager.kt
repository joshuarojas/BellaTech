package com.samuelchowi.bellatech.main.custom

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.viewpager.widget.ViewPager

class CustomizedViewPager(context: Context, attrs: AttributeSet?) : ViewPager(context, attrs) {

    var shouldSwipe: Boolean = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (shouldSwipe)
            return super.onTouchEvent(ev)
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (shouldSwipe)
            return super.onInterceptTouchEvent(ev)
        return false
    }
}