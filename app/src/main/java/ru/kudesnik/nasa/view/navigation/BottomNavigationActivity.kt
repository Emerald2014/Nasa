package ru.kudesnik.nasa.view.navigation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
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
                    true
                }
                R.id.bottom_view_mars -> {
                    true
                }
                R.id.bottom_view_system -> {
                    true
                }
                else -> {
                    false
                }
            }
        }
    }
}