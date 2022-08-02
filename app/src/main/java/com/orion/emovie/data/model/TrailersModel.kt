package com.orion.emovie.data.model

import com.google.gson.annotations.SerializedName

class TrailersModel (
    val id: Int,
    @SerializedName("results")
    val results: List<Video>
)