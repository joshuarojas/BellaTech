package com.samuelchowi.bellatech.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.samuelchowi.bellatech.main.model.StepModel

class MainAdapter(fm: FragmentManager, private val items: List<StepModel>) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = StepFragment.instance(items[position])

    override fun getCount(): Int = items.size
}