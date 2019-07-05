package com.samuelchowi.bellatech.main.custom

import android.content.Context
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.samuelchowi.bellatech.R
import com.samuelchowi.bellatech.common.isVisible
import kotlinx.android.synthetic.main.view_navigation_controller.view.*

class NavigationController(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

    var viewPager: ViewPager? = null
    var onFinishListener: (() -> Unit)? = null

    fun initializeViews() {
        if (viewPager == null)
            throw NullPointerException("This view needs a view pager to work properly")

        addView(LayoutInflater.from(context).inflate(R.layout.view_navigation_controller, this, false))

        setUpViewPagerListener()
        setUpIndicator()
        setUpButtons()
    }

    private fun setUpViewPagerListener() {
        viewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                // Empty
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // Empty
            }

            override fun onPageSelected(position: Int) {
                pickIndicator()
            }
        })
    }

    private fun setUpIndicator() {
        tvwIndicator.isVisible = true
        tvwIndicator.text = "\u2000"
        repeat(viewPager?.adapter?.count ?: 1) { tvwIndicator.append("\u25cf\u2000") }
        pickIndicator()
    }

    private fun setUpButtons() {
        tvwBack.setOnClickListener {
            if (viewPager?.currentItem?.compareTo(0) == 1)
                viewPager?.currentItem = viewPager?.currentItem?.minus(1) ?: 0
        }

        tvwNext.setOnClickListener {
            if (viewPager?.currentItem == viewPager?.adapter?.count?.minus(1))
                onFinishListener?.invoke()
            else {
                viewPager?.currentItem = viewPager?.currentItem?.plus(1) ?: 0
            }
        }
    }

    private fun pickIndicator() {
        viewPager?.currentItem?.let {
            if (tvwIndicator.text.isNotEmpty() && (it * 2) >= 0 && (it * 2 + 2) < tvwIndicator.text.length) {
                val builder = SpannableStringBuilder(tvwIndicator.text.toString())
                builder.setSpan(
                    ForegroundColorSpan(ContextCompat.getColor(context, R.color.colorPrimary)),
                    it * 2,
                    it * 2 + 2,
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                )
                tvwIndicator.text = builder
            }
        }
    }
}