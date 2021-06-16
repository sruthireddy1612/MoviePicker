package com.nike.moviepicker.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nike.moviepicker.data.model.Event
import com.nike.moviepicker.data.model.MovieList
import com.nike.moviepicker.data.model.Output
import com.nike.moviepicker.data.source.MoviesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverMoviesViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _snackBarMsg = MutableLiveData<Event<String>>()
    val snackBarMsg: LiveData<Event<String>>
        get() = _snackBarMsg

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    private val _movieList = MutableLiveData<MovieList>()
    val movieList: LiveData<MovieList>
        get() = _movieList

    fun discoverMovies() {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            val moviesResult = moviesRepository.discoverMovies()
            if (moviesResult is Output.Success) {
                _movieList.postValue(moviesResult.data)
            } else if (moviesResult is Output.Error) {
                moviesResult.errorMsg?.let {
                    _snackBarMsg.postValue(Event(it))
                }
            }
            _isLoading.postValue(false)
        }
    }
}