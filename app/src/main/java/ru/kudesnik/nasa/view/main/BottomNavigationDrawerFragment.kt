package ru.kudesnik.nasa.view.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.BottomNavigationLayoutBinding
import ru.kudesnik.nasa.view.layout.LayoutActivity
import ru.kudesnik.nasa.view.navigation.BottomNavigationActivity
import ru.kudesnik.nasa.view.navigation.NavigationActivity

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

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_one -> {
                    startActivity(Intent(requireActivity(), NavigationActivity::class.java))
                }
                R.id.navigation_two -> {
                    startActivity(Intent(requireContext(), BottomNavigationActivity::class.java))
                }
                R.id.navigation_three -> {
                    startActivity(Intent(requireContext(), LayoutActivity::class.java))

                }
            }
            true
        }

    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}

