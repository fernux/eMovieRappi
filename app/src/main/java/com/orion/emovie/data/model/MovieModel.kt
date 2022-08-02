package com.orion.emovie.data.model

import com.google.gson.annotations.SerializedName

data class MovieModel(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("original_language")
    val language:String,
    var type:Int
)
