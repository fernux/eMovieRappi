package com.orion.emovie.ui.view

import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orion.emovie.R
import com.orion.emovie.data.model.MovieDetailsModel
import com.orion.emovie.ui.adapter.MovieVideoAdapter
import com.orion.emovie.ui.viewmodel.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.*

@AndroidEntryPoint
class MovieDetailsActivity : AppCompatActivity() {

    val movieDetailsViewModel: MovieDetailsViewModel by viewModels()
     lateinit var tvTitle:TextView
    lateinit var tvTagline:TextView
    lateinit var tvDate:TextView
    lateinit var tvRating:TextView
    lateinit var tvRuntime:TextView
    lateinit var tvOverview:TextView
    lateinit var tvRevenu:TextView
    lateinit var tvBudget:TextView
    lateinit var ivImagen: ImageView
    lateinit var rvVideo: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_movie)
        val movieId: Int = intent.getIntExtra("id", 1)

        var isConnected: Boolean

        val connectivityManager = this.applicationContext.getSystemService(
            CONNECTIVITY_SERVICE
        ) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        isConnected= activeNetwork != null && activeNetwork.isConnectedOrConnecting
        if(isConnected) {
            movieDetailsViewModel.getMovieTrailer(movieId)
            movieDetailsViewModel.getMovieDetails(movieId)
        }

        tvTitle = findViewById(R.id.movie_title)

        tvTagline = findViewById(R.id.movie_tagline)
        tvDate = findViewById(R.id.movie_release_date)
        tvRating = findViewById(R.id.movie_rating)

        tvRuntime = findViewById(R.id.movie_runtime)
        tvOverview = findViewById(R.id.movie_overview)
        tvBudget = findViewById(R.id.movie_budget)
        tvRevenu = findViewById(R.id.movie_revenue)
        ivImagen = findViewById(R.id.iv_movie_poster)
        rvVideo = findViewById(R.id.list_trailer)
        rvVideo.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        movieDetailsViewModel.MovieDetails.observe( this, Observer { currentMovie ->
            bindUI(currentMovie)
        })

        movieDetailsViewModel.MovieVideo.observe( this, Observer { it ->
            initView()
        })

    }

    private fun initView() {
        rvVideo.adapter = MovieVideoAdapter(movieDetailsViewModel.MovieVideo){
            onItemSelected(it)
        }
    }

    private fun onItemSelected(it: String) {
        val playVideoIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
        this.startActivity(playVideoIntent)
    }

    fun bindUI(it: MovieDetailsModel){
        val POSTER_BASE_URL = "https://image.tmdb.org/t/p/w342"
        tvTitle.text = it.title
        tvTagline.text = it.tagline
        tvDate.text = it.releaseDate
        tvRating.text = it.rating.toString()
        tvRuntime.text = it.runtime.toString() + " minutes"
        tvOverview.text = it.overview

        val formatCurrency = NumberFormat.getCurrencyInstance(Locale.US)
        tvBudget.text = formatCurrency.format(it.budget)
        tvRevenu.text = formatCurrency.format(it.revenue)


        val moviePosterURL = POSTER_BASE_URL + it.posterPath
        Glide.with(this)
            .load(moviePosterURL)
            .into(ivImagen)



    }


}