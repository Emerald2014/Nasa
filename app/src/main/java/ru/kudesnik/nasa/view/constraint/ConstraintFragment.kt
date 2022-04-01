package ru.kudesnik.nasa.view.constraint

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kudesnik.nasa.databinding.FragmentConstraintBinding

class ConstraintFragment: Fragment() {
    private var _binding: FragmentConstraintBinding? = null
    private val binding: FragmentConstraintBinding
    get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConstraintBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}