package com.nike.moviepicker.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nike.moviepicker.R
import com.nike.moviepicker.databinding.ActivityMoviePickerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviePickerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<ActivityMoviePickerBinding>(
            this,
            R.layout.activity_movie_picker
        )
    }
}