package com.orion.emovie.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.domain.usecase.GetMovieToRatedUseCase
import com.orion.emovie.domain.usecase.GetMovieUpcomingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getMovieToRatedUseCase: GetMovieToRatedUseCase ,
    private val getMovieUpcomingUseCase : GetMovieUpcomingUseCase
): ViewModel() {

    val movieTopRatedModelLst = MutableLiveData<MutableList<MovieModel>>()
    val movieUpcomingLst = MutableLiveData<MutableList<MovieModel>>()


    fun getTopRated(){
        viewModelScope.launch {
            val respuesta=  getMovieToRatedUseCase()
            if(respuesta.count()>0)
                movieTopRatedModelLst.postValue(respuesta)
            else
                movieTopRatedModelLst.postValue(arrayListOf())
        }
    }


    fun getUpcoming(){
        viewModelScope.launch {
            val respuesta=getMovieUpcomingUseCase()
            if(respuesta.count()>0)
                movieUpcomingLst.postValue(respuesta)
            else
                movieUpcomingLst.postValue(arrayListOf())
        }
    }
}