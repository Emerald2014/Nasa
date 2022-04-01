package ru.kudesnik.nasa.view.chips

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.chip.Chip
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.FragmentChipsBinding
import ru.kudesnik.nasa.databinding.FragmentMainBinding
import ru.kudesnik.nasa.view.KEY_CURRENT_THEME
import ru.kudesnik.nasa.view.MainActivity
import ru.kudesnik.nasa.view.main.PictureOfTheDayFragment

class ChipsFragment : Fragment() {
    private var _binding: FragmentChipsBinding? = null
    private val binding: FragmentChipsBinding
        get() = _binding!!

    private lateinit var parentActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        parentActivity = activity as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentChipsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) = with(binding) {
        super.onViewCreated(view, savedInstanceState)
        when (KEY_CURRENT_THEME) {
            R.style.ThemeNasaGreen.toString() -> {
                chipGroupChoiceTheme
            }
        }
        chipGreen.setOnClickListener {
            Toast.makeText(context, "Выбран Chip on ${chipGreen.text} ", Toast.LENGTH_SHORT).show()
            parentActivity.setCurrentTheme(R.style.ThemeNasaGreen)
            parentActivity.recreate()

        }
        chipOrange.setOnClickListener {
            Toast.makeText(context, "Выбран Chip on ${chipOrange.text} ", Toast.LENGTH_SHORT).show()
            parentActivity.setCurrentTheme(R.style.ThemeNasaOrange)
            parentActivity.recreate()

        }

        chipGroupChoiceTheme.setOnCheckedChangeListener { chipGroupChoiceTheme, position ->
            chipGroupChoiceTheme.findViewById<Chip>(position)?.let {
                Toast.makeText(context, "Выбран ${it.text} Chip on $position", Toast.LENGTH_SHORT)
                    .show()
            }
        }
//            when(position) {
//                1 -> {
//                    Toast.makeText(context, "Выбран ${(chipGroupChoiceTheme.findViewById<Chip>(position)).text} Chip on $position", Toast.LENGTH_SHORT).show()
//                }
//                2 -> {
//                    Toast.makeText(context, "Выбран ${(chipGroupChoiceTheme.findViewById<Chip>(position)).text} Chip on $position", Toast.LENGTH_SHORT).show()
//
//                }
//            }
//            chipGroupChoiceTheme.findViewById<Chip>(position)?.let {
//
//
//                parentActivity.setCurrentTheme(R.style.ThemeNasaOrange)
//                parentActivity.recreate()
//
//
//            }


        chipGroup.setOnCheckedChangeListener { chipGroup, position ->
            chipGroup.findViewById<Chip>(position)?.let {
//                parentActivity.setCurrentTheme(R.style.ThemeNasaGreen)
//parentActivity.recreate()

                when (it.id) {
                    1 -> {
                        parentActivity.setCurrentTheme(R.style.ThemeNasaGreen)
                        Toast.makeText(context, "Выбран ${it.text}", Toast.LENGTH_SHORT).show()

                    }
                    2 -> {
                        Toast.makeText(context, "Выбран $position", Toast.LENGTH_SHORT).show()

                        parentActivity.setCurrentTheme(R.style.ThemeNasaOrange)
                    }
                }
            }
        }

//        chip_close.setOnCloseIconClickListener {
//            Toast.makeText(
//                context,
//                "Close is Clicked",
//                Toast.LENGTH_SHORT
//            ).show()
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}
