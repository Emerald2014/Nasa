package ru.kudesnik.nasa.view.constraint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.ActivityLayoutBinding

class LayoutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBottomNavigationView()

    }

    private fun initBottomNavigationView() {
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_constraint -> {
                    navigateUpTo(ConstraintFragment())
                    true
                }
                R.id.bottom_coordinator -> {
//                    navigateUpTo(CoordinatorFragment())
                    true
                }
                R.id.bottom_motion -> {
//                    navigateUpTo(MotionFragment())
                    true
                }
                else -> {
                    false
                }
            }
        }
        binding.bottomNavigationView.selectedItemId = R.id.bottom_constraint

    }

    private fun navigateUpTo(f: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, f).commit()
    }
}