package ru.kudesnik.nasa.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.ActivityBottomNavigationBinding
import ru.kudesnik.nasa.databinding.ActivityNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {
    lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_view_earth -> {
                    navigationTo(EarthFragment())
                    true
                }
                R.id.bottom_view_mars -> {
                    navigationTo(MarsFragment())

                    true
                }
                R.id.bottom_view_system -> {
                    navigationTo(SystemFragment())

                    true
                }
                else -> {
                    false
                }
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_mars
        val badge = binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        badge.number = 9999
    }

    private fun navigationTo(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }
}