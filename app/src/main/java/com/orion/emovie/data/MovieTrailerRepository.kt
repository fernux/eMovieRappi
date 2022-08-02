package com.orion.emovie.data

import com.orion.emovie.data.model.Video
import com.orion.emovie.data.network.MovieDetailService
import javax.inject.Inject

class MovieTrailerRepository @Inject constructor(
    private val  api : MovieDetailService,
) {
    suspend fun getMovieVideoFromApi(id:Int): List<Video> {
        return api.getMovieVideo(id)
    }

}