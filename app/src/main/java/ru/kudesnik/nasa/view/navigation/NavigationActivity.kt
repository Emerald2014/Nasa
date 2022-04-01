package ru.kudesnik.nasa.view.navigation

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.ActivityNavigationBinding
import ru.kudesnik.nasa.view.KEY_CURRENT_THEME
import ru.kudesnik.nasa.view.KEY_SP

class NavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getCurrentTheme())

        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)

        /*binding.tabLayout.getTabAt(EARTH_KEY)?.setIcon(R.drawable.ic_earth)
        binding.tabLayout.getTabAt(MARS_KEY)?.setIcon(R.drawable.ic_mars)
        binding.tabLayout.getTabAt(System_KEY)?.setIcon(R.drawable.ic_system)*/

        binding.tabLayout.getTabAt(EARTH_KEY)
            ?.setCustomView(R.layout.activity_navigation_tablayout_item_earth)
        binding.tabLayout.getTabAt(MARS_KEY)
            ?.setCustomView(R.layout.activity_navigation_tablayout_item_mars)
        binding.tabLayout.getTabAt(System_KEY)
            ?.setCustomView(R.layout.activity_navigation_tablayout_item_system)
    }

    private fun getCurrentTheme(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return (sharedPreferences.getInt(KEY_CURRENT_THEME, -1))
    }
}