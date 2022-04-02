package ru.kudesnik.nasa.view.layout.motion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.kudesnik.nasa.databinding.FragmentConstraintBinding
import ru.kudesnik.nasa.databinding.FragmentMotionStartBinding

class MotionFragment: Fragment() {
    private var _binding: FragmentMotionStartBinding? = null
    private val binding: FragmentMotionStartBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMotionStartBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}