package com.mg.recipe.ui.fragments.recipes.details.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(
    private val resultBundle: Bundle,
    private val fragment: ArrayList<Fragment>,
    private val titles: ArrayList<String>,
    fragmentManager: FragmentManager
) : FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int = fragment.size

    override fun getItem(position: Int): Fragment {
        fragment[position].arguments = resultBundle
        return fragment[position]
    }

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}