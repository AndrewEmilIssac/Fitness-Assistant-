

package com.fitness.assistant.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fitness.assistant.ui.fragments.home.DayFragment


/**
 * View Pager Adapter for HomeFragment
 */
class DayPagerAdapter(val fragments: ArrayList<DayFragment>, fm: FragmentManager) :

    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.size
    }

}

