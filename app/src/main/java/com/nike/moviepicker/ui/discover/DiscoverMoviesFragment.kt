package com.nike.moviepicker.ui.discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import com.nike.moviepicker.R
import com.nike.moviepicker.adapter.DiscoverMoviesAdapter
import com.nike.moviepicker.adapter.MovieMarginDecoration
import com.nike.moviepicker.databinding.FragmentMoviePickerBinding
import com.nike.moviepicker.viewmodel.DiscoverMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DiscoverMoviesFragment : Fragment() {

    private lateinit var binding: FragmentMoviePickerBinding
    private val viewModel: DiscoverMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoviePickerBinding.inflate(inflater, container, false)
        context ?: return binding.root

        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpViewPager()
        viewModel.discoverMovies()
        setUpObservers()
    }

    private fun setUpViewPager() {
        binding.vpMovieCarousel.offscreenPageLimit = 1


        val nextItemVisiblePx = resources.getDimension(R.dimen.viewpager_next_item_visible)
        val currentItemHorizontalMarginPx =
            resources.getDimension(R.dimen.viewpager_current_item_horizontal_margin)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
        }
        binding.vpMovieCarousel.setPageTransformer(pageTransformer)

        val itemDecoration = MovieMarginDecoration(
            requireContext(),
            R.dimen.viewpager_current_item_horizontal_margin
        )
        binding.vpMovieCarousel.addItemDecoration(itemDecoration)
    }

    private fun setUpObservers() {
        viewModel.movieList.observe(viewLifecycleOwner, {
            binding.vpMovieCarousel.adapter = DiscoverMoviesAdapter(it.results)
            binding.vpMovieCarousel.post {
                binding.vpMovieCarousel.setCurrentItem(
                    Int.MAX_VALUE / 2 + it.results.size / 2,
                    false
                )
            }
        })
        viewModel.snackBarMsg.observe(viewLifecycleOwner, {
            it.getContentIfNotHandled()?.let { msg ->
                Snackbar.make(binding.root, msg, Snackbar.LENGTH_LONG).show()
            }
        })
    }
}