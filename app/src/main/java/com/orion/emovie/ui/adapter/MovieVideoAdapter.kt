package com.orion.emovie.ui.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.orion.emovie.R
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.data.model.TrailersModel
import com.orion.emovie.data.model.Video

class MovieVideoAdapter (private val MovieList: MutableLiveData<MutableList<Video>>, private  val onClickListener:(String)-> Unit): RecyclerView.Adapter<MovieVideoViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideoViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_trailer, parent, false)
        layout.accessibilityDelegate = AccessibilityVideo
        return MovieVideoViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieVideoViewHolder, position: Int) {
        val item = MovieList.value?.get(position)
        if (item != null) {
            holder.render(item,onClickListener)
        }
    }

    override fun getItemCount(): Int {
        if (MovieList.value?.size   != null) {
            return  MovieList.value!!.size
        }
        return 0
    }
    companion object AccessibilityVideo : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            val customString = host?.context?.getString(R.string.app_name)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}