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

class MovieTopRatedAdapter(private val MovieList: MutableLiveData<MutableList<MovieModel>>,private  val onClickListener:(MovieModel)-> Unit): RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layout = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie, parent, false)

        layout.accessibilityDelegate = Accessibility
        return MovieViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = MovieList.value?.get(position)
        if (item != null) {
            holder.render(item,onClickListener)
        }
    }

    override fun getItemCount(): Int {
        if (MovieList.value!!.size   != null) {
            return  MovieList.value!!.size
        }
        return 0
    }
    companion object Accessibility : View.AccessibilityDelegate() {
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