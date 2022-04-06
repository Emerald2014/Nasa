package ru.kudesnik.nasa.view.animation

import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.ActivityAnimationBinding
import ru.kudesnik.nasa.databinding.ActivityNavigationBinding
import ru.kudesnik.nasa.view.KEY_CURRENT_THEME
import ru.kudesnik.nasa.view.KEY_SP
import java.util.*

class AnimationActivity : AppCompatActivity() {
    lateinit var binding: ActivityAnimationBinding

    private val sRandom: Random = Random()
    private lateinit var wheelImage: ImageView
    private var lastAngle = -1
    private var scoreP: TextView? = null
    private var scoreRes = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getCurrentTheme())

        binding = ActivityAnimationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        wheelImage = binding.imgPlanet
        binding.imgSun.setOnClickListener { spinSystem() }

    }

    private fun getCurrentTheme(): Int {
        val sharedPreferences: SharedPreferences = getSharedPreferences(KEY_SP, MODE_PRIVATE)
        return (sharedPreferences.getInt(KEY_CURRENT_THEME, -1))
    }

    private fun spinSystem() {
        val angle = sRandom.nextInt(3600 - 360) + 360
        // Центр вращения
        val pivotX = (wheelImage.width / 2).toFloat()
        val pivotY = (wheelImage.height / 2).toFloat()
        val animation: Animation = RotateAnimation(
            (if (lastAngle == -1) 0 else lastAngle).toFloat(),
            angle.toFloat(), pivotX, pivotY
        )
        lastAngle = angle
        scoreRes += angle
        animation.duration = 2500
        animation.fillAfter = true

        wheelImage.startAnimation(animation)

        scoreP?.text = scoreRes.toString()
    }
}