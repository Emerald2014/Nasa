package ru.kudesnik.nasa.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import ru.kudesnik.nasa.R
import ru.kudesnik.nasa.databinding.FragmentMainBinding
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
        viewModel.getLiveData().observe(viewLifecycleOwner) { renderData(it) }
        viewModel.sendServerRequest()

        binding.inputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data =
                    Uri.parse("https://ru.wikipedia.org/wiki/${binding.inputEditText.text.toString()}")
            })
        }

        bottomSheetBehavior = BottomSheetBehavior.from(binding.included.bottomSheetContainer)
    bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object: BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
//            when (newState) {
//
//                BottomSheetBehavior.STATE_COLLAPSED -> {
//                    TODO()
//                }
//                BottomSheetBehavior.STATE_DRAGGING -> {
//                    TODO()
//                }
//                BottomSheetBehavior.STATE_EXPANDED -> {
//                    TODO()
//                }
//                BottomSheetBehavior.STATE_HALF_EXPANDED -> {
//                    TODO()
//                }
//                BottomSheetBehavior.STATE_HIDDEN -> {
//                    TODO()
//                }
//                BottomSheetBehavior.STATE_SETTLING -> {
//                    TODO()
//                }
//            }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
//                TODO("Not yet implemented")
            }

        })

    }

    private fun renderData(pictureOfTheDayState: PictureOfTheDayState) {
        when (pictureOfTheDayState) {
            is PictureOfTheDayState.Error -> {
                //HomeWork
            }
            is PictureOfTheDayState.Loading -> {
                //HomeWork
            }
            is PictureOfTheDayState.Success -> {
                val serverResponseData = pictureOfTheDayState.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //showError("Сообщение, что ссылка пустая")
//                    toast("Link is empty")
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

    companion object {
        fun newInstance() = PictureOfTheDayFragment()
    }
}