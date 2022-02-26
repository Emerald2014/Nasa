package ru.kudesnik.nasa.view.main

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
import ru.kudesnik.nasa.view.MainActivity
import ru.kudesnik.nasa.view.chips.ChipsFragment
import ru.kudesnik.nasa.viewmodel.PictureOfTheDayState
import ru.kudesnik.nasa.viewmodel.PictureOfTheDayViewModel

class PictureOfTheDayFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
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
            FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendServerRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
            }

        })
        binding.fab.setOnClickListener {
            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar_other_screen)
                isMain = false
            } else {
                binding.bottomAppBar.navigationIcon = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_hamburger_menu_bottom_bar
                )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.fab.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_plus_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
                isMain = true
            }
        }
    }

    var isMain: Boolean = true


    private fun renderData(pictureOfTheDayState: PictureOfTheDayState) = with(binding) {
        when (pictureOfTheDayState) {
            is PictureOfTheDayState.Error -> {
                progressBar.visibility = View.GONE
                fragmentMainRoot.showSnackBar(
                    getString(R.string.error),
                    getString(R.string.reload),
                    {
                        viewModel.sendServerRequest()
                    })
            }
            is PictureOfTheDayState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is PictureOfTheDayState.Success -> {
                progressBar.visibility = View.GONE

                binding.included.bottomSheetDescriptionHeader.text =
                    pictureOfTheDayState.serverResponseData.title
                binding.included.bottomSheetDescription.text =
                    pictureOfTheDayState.serverResponseData.explanation
                val serverResponseData = pictureOfTheDayState.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Toast.makeText(requireContext(), "Ссылка пустая", Toast.LENGTH_SHORT).show()
                } else {
                    //showSuccess()
                    binding.imageView.load(url) {
//                        lifecycle(this@PictureOfTheDayFragment)
                        error(R.drawable.no_image)
                        placeholder(R.drawable.no_poster)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Toast.makeText(requireContext(), "app_bar_fav", Toast.LENGTH_SHORT)
                .show()
            R.id.app_bar_settings -> {
                Toast.makeText(
                    requireContext(),
                    "app_bar_settings",
                    Toast.LENGTH_SHORT
                ).show()
                activity?.supportFragmentManager?.beginTransaction()
                    ?.add(R.id.container, ChipsFragment())?.addToBackStack(null)?.commit()
            }
            android.R.id.home -> BottomNavigationDrawerFragment().show(
                requireActivity().supportFragmentManager,
                ""
            )
        }
        return super.onOptionsItemSelected(item)
    }

    fun View.showSnackBar(
        text: String,
        actionText: String,
        action: (View) -> Unit,
        length: Int = Snackbar.LENGTH_INDEFINITE
    ) {
        Snackbar.make(this, text, length).setAction(actionText, action).show()
    }

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}