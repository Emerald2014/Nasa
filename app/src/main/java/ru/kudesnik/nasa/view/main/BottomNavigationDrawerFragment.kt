package ru.kudesnik.nasa.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.BottomNavigationLayoutBinding
import ru.kudesnik.nasa.databinding.FragmentMainBinding

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var _binding: BottomNavigationLayoutBinding? = null
    private val binding: BottomNavigationLayoutBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            BottomNavigationLayoutBinding.inflate(inflater, container, false)
        return binding.root

    }
    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}

