package com.orion.emovie.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orion.emovie.data.model.Video
import com.orion.emovie.databinding.ItemTrailerBinding

class MovieVideoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val binding = ItemTrailerBinding.bind(view)

    fun render(trailer: Video, onClickListener:(String)-> Unit){
        val url = "https://img.youtube.com/vi/" + trailer.key + "/hqdefault.jpg"
        val url2 = "https://www.youtube.com/watch?v="+trailer.key
        Glide.with(binding.itemVideoCover.context).load(url).into(binding.itemVideoCover)
        binding.itemVideoTitle.text = trailer.name
        itemView.setOnClickListener {
            onClickListener(url2)
        }
    }

}