package ru.kudesnik.nasa.view.navigation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.snackbar.Snackbar
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.FragmentMainBinding
import ru.kudesnik.nasa.databinding.FragmentMarsBinding
import ru.kudesnik.nasa.databinding.FragmentSystemBinding
import ru.kudesnik.nasa.view.MainActivity
import ru.kudesnik.nasa.view.chips.ChipsFragment
import ru.kudesnik.nasa.viewmodel.PictureOfTheDayState
import ru.kudesnik.nasa.viewmodel.PictureOfTheDayViewModel

class MarsFragment : Fragment() {

    private var _binding: FragmentMarsBinding? = null
    private val binding: FragmentMarsBinding
        get() = _binding!!

    private val viewModel: PictureOfTheDayViewModel by lazy {
        ViewModelProvider(this).get(PictureOfTheDayViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            FragmentMarsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    companion object {
        fun newInstance() = MarsFragment()
    }
}