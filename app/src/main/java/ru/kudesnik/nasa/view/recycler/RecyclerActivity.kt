package ru.kudesnik.nasa.view.recycler

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.ActivityRecyclerBinding
import ru.kudesnik.nasa.view.KEY_CURRENT_THEME
import ru.kudesnik.nasa.view.KEY_SP
import ru.kudesnik.nasa.view.main.PictureOfTheDayFragment

class RecyclerActivity : AppCompatActivity() {
    lateinit var binding: ActivityRecyclerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getCurrentTheme())
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
        val data = arrayListOf(
            Data("Earth", "Доп. текст"),
            Data("Earth", "Доп. текст"),
            Data("Earth", type = TYPE_MARS),
            Data("Earth", "Доп. текст"),
            Data("Earth", type = TYPE_MARS),
            Data("Earth", "Доп. текст"),
            Data("Earth", type = TYPE_MARS),
            Data("Earth", "Доп. текст"),
        )
        val adapter = RecyclerActivityAdapter(object : OnClickItemListener {
            override fun onItemClick(data: Data) {
                Toast.makeText(this@RecyclerActivity, "Мы молодцы ${data.name}", Toast.LENGTH_SHORT)
                    .show()
            }
        })
        adapter.setData(data)
        binding.recyclerView.adapter = adapter
    }

    private fun getCurrentTheme(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return (sharedPreferences.getInt(KEY_CURRENT_THEME, -1))
    }
}