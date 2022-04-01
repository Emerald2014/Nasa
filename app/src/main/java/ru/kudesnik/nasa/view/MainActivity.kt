package ru.kudesnik.nasa.view

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.security.keystore.KeyNotYetValidException
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.view.main.PictureOfTheDayFragment
 val KEY_SP = "sp"
val KEY_CURRENT_THEME = "current_theme"
class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getCurrentTheme())
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    fun setCurrentTheme(currentTheme: Int) {
        val sharedPreferences: SharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt(KEY_CURRENT_THEME, currentTheme)
        editor.apply()
//        recreate()

    }

    private fun getCurrentTheme(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return (sharedPreferences.getInt(KEY_CURRENT_THEME, -1))
    }

//    fun getRealStyle(currentTheme: Int): Int {
//        when(currentTheme) {
//            ThemeNasaGreen -> {
//                return R.style.ThemeNasaGreen
//            }
//
//        }
//    }
}