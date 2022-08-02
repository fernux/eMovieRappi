package com.orion.emovie.domain.usecase

import com.orion.emovie.data.MovieDetailsRepository
import com.orion.emovie.data.model.MovieDetailsModel
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository : MovieDetailsRepository){
    suspend operator fun invoke(id:Int):MovieDetailsModel {
        return  repository.getMovieDetailFromApi(id)

    }
}