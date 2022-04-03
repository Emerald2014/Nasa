package ru.kudesnik.nasa.view.layout.coordinator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.Fragment
import ru.kudesnik.nasa.databinding.FragmentCoordinatorBinding

class CoordinatorFragment : Fragment() {
    private var _binding: FragmentCoordinatorBinding? = null
    private val binding: FragmentCoordinatorBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCoordinatorBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val behavior = ButtonBehavior(requireContext(), null)
        (binding.fabMini.getLayoutParams() as CoordinatorLayout.LayoutParams).behavior = behavior

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}