package com.orion.emovie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.emovie.data.model.MovieDetailsModel
import com.orion.emovie.data.model.Video
import com.orion.emovie.domain.usecase.GetMovieDetailsUseCase
import com.orion.emovie.domain.usecase.GetMovieTrailerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val getMovieDatailsUseCase: GetMovieDetailsUseCase,
    private val getMovieTrailerUseCase: GetMovieTrailerUseCase,
): ViewModel() {

    val MovieDetails = MutableLiveData<MovieDetailsModel>()
    val MovieVideo = MutableLiveData<MutableList<Video>>()
    fun getMovieDetails(id:Int){
        viewModelScope.launch {
            val respuesta=  getMovieDatailsUseCase(id)
            MovieDetails.postValue(respuesta)
        }
    }
    fun getMovieTrailer(id: Int){
        viewModelScope.launch {
            val response = getMovieTrailerUseCase(id)
            MovieVideo.postValue(response.toMutableList())
        }
    }
}