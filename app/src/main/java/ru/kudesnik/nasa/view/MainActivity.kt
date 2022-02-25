package ru.kudesnik.nasa.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.view.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }
}