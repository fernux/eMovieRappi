package com.orion.emovie.data

import com.orion.emovie.data.model.MovieDetailsModel
import com.orion.emovie.data.network.MovieDetailService
import javax.inject.Inject

class MovieDetailsRepository @Inject constructor(
    private val  api : MovieDetailService,
) {
    suspend fun getMovieDetailFromApi(id:Int): MovieDetailsModel {
        return api.getMovieDetails(id)
    }
}