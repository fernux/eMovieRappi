package com.orion.emovie.domain.usecase

import com.orion.emovie.data.MovieTrailerRepository
import com.orion.emovie.data.model.Video
import javax.inject.Inject

class GetMovieTrailerUseCase @Inject constructor(private val repository : MovieTrailerRepository){
    suspend operator fun invoke(id:Int): List<Video> {
        return  repository.getMovieVideoFromApi(id)
    }
}