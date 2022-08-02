package com.orion.emovie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.databinding.ItemMovieBinding

class MovieViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemMovieBinding.bind(view)
     val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"

    fun render(movie:MovieModel,onClickListener:(MovieModel)-> Unit){
        Glide
            .with(binding.ivMovie.context)
            .load(POSTER_BASE_URL+movie.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.DATA)
            .into(binding.ivMovie)
        itemView.setOnClickListener {
            onClickListener(movie)
        }
    }

}