package com.orion.emovie.domain.model


import com.orion.emovie.data.database.entities.MovieEntity
import com.orion.emovie.data.model.MovieModel

data class Movie(val id:Int,val title:String, val posterPath:String,val releaseDate:String,val language: String, val type:Int)

fun MovieModel.toDomain() = Movie(id,title,releaseDate,posterPath,language,type)
fun MovieEntity.toDomain() = MovieModel(id = id, title = title, releaseDate = releaseDate, posterPath = posterPath, language = language, type = type)

