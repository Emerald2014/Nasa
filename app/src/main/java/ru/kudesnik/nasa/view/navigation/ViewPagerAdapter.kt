package ru.kudesnik.nasa.view.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

private const val EARTH_KEY = 0
private const val MARS_KEY = 1
private const val System_KEY = 2

class ViewPagerAdapter(private val fragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(fragmentManager) {
    private val fragments = arrayOf(EarthFragment(), MarsFragment(), SystemFragment())
    override fun getCount(): Int {
        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
        return fragments[position]
    }
}