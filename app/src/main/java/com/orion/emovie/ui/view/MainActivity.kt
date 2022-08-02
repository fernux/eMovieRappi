package com.orion.emovie.ui.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.emovie.EmovieApplication
import com.orion.emovie.R
import com.orion.emovie.data.model.MovieModel
import com.orion.emovie.ui.adapter.MovieTopRatedAdapter
import com.orion.emovie.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    val mainViewModel:MainViewModel by viewModels()
    private lateinit var rvTopRated:RecyclerView
    private lateinit var rvUpcoming:RecyclerView
    private lateinit var rvRecommended:RecyclerView
    private lateinit var spLanguage:Spinner
    private lateinit var spYear:Spinner
    private var selectLanguage=""
    private var selectYear=""
    private  var lstLanguage:MutableList<String> = arrayListOf()
    private  var lstYear:MutableList<String> = arrayListOf()
    private lateinit var lstMovieTopRated:MutableLiveData<MutableList<MovieModel>>
    private  var lstTemporal:MutableLiveData<MutableList<MovieModel>> = MutableLiveData()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayShowCustomEnabled(true)

        mainViewModel.getUpcoming()
        mainViewModel.getTopRated()
        rvTopRated = findViewById(R.id.rv_rated)
        rvUpcoming = findViewById(R.id.rv_upcoming)
        rvRecommended = findViewById(R.id.rv_recommended)
        spLanguage = findViewById(R.id.sp_language)
        spYear  = findViewById(R.id.sp_year)

        observeMovieUpcoming()
        observeMovieTopRated()
        lstLanguage.add("NO LANGUAGE")
        lstYear.add("NO YEAR")


        spLanguage.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            @SuppressLint("SetTextI18n")
            override fun onNothingSelected(parent: AdapterView<*>?)
            {

                selectLanguage = "please select an option"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
            {


                    selectLanguage = lstLanguage.distinct()[position]

                    if (selectLanguage.equals("NO LANGUAGE")) {
                        lstTemporal.value = mainViewModel.movieTopRatedModelLst.value
                    } else {

                        lstTemporal.value = mainViewModel.movieTopRatedModelLst.value?.filter {
                            it.language.equals(
                                when (selectLanguage) {
                                    "ENGLISH" -> {
                                        "en"
                                    }
                                    "HINDI" -> {
                                        "hi"
                                    }
                                    "SPANISH" -> {
                                        "es"
                                    }
                                    "JAPANESE" -> {
                                        "ja"
                                    }
                                    "KOREAN" -> {
                                        "ko"
                                    }
                                    "ITALIAN" -> {
                                        "it"
                                    }
                                    else -> {
                                        "en"
                                    }
                                }
                            )
                        }?.toMutableList()
                    }
                    rvRecommended.adapter = MovieTopRatedAdapter(lstTemporal) {
                        onItemSelected(it)
                    }

            }
        }
        spYear.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{

            @SuppressLint("SetTextI18n")
            override fun onNothingSelected(parent: AdapterView<*>?)
            {

                selectYear = "please select an option"
            }
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                    selectYear = lstYear.distinct().sortedDescending()[position]

                    if (selectYear.equals("NO YEAR")) {
                        lstTemporal.value = mainViewModel.movieTopRatedModelLst.value
                    } else {
                        lstTemporal.value = mainViewModel.movieTopRatedModelLst.value?.filter {
                            it.releaseDate.split("-")[0].equals(selectYear)
                        }?.toMutableList()

                    }
                    rvRecommended.adapter = MovieTopRatedAdapter(lstTemporal) {
                        onItemSelected(it)
                    }


            }
        }


    }

    override fun onStart() {
        super.onStart()
        EmovieApplication.prefs.name = 1
        rvUpcoming.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        rvTopRated.layoutManager =  LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL ,false)
        rvRecommended.layoutManager = GridLayoutManager(this,2)
    }





    private fun observeMovieUpcoming() {

        mainViewModel.movieUpcomingLst.observe( this, Observer { currentMovie ->
            initRecyclerView()
        })
    }
    private fun observeMovieTopRated() {

        mainViewModel.movieTopRatedModelLst.observe( this, Observer { currentMovie ->
            initRecyclerView2()

            for(elemento in currentMovie){
                lstLanguage.add(
                    when (elemento.language){
                        "en" -> {"ENGLISH"}
                        "hi"->{"HINDI"}
                        "es"->{"SPANISH"}
                        "ja"->{"JAPANESE"}
                        "ko"->{"KOREAN"}
                        "it"->{"ITALIAN"}
                        else -> {"NO LANGUAGE"}
                    }

                )
                lstYear.add(elemento.releaseDate.split("-")[0])
            }
            iniSpinner()

        })
    }

    private fun iniSpinner() {
        var adapter:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, lstLanguage.distinct())
        spLanguage.adapter = adapter
        var adapterYear:ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, lstYear.distinct().sortedDescending())
        spYear.adapter = adapterYear
    }

    fun initRecyclerView(){
        rvUpcoming.adapter = MovieTopRatedAdapter(mainViewModel.movieUpcomingLst){
            onItemSelected(it)
        }

    }
    fun initRecyclerView2(){
        lstMovieTopRated =  mainViewModel.movieTopRatedModelLst

        rvTopRated.adapter = MovieTopRatedAdapter(mainViewModel.movieTopRatedModelLst){
            onItemSelected(it)

        }

        rvRecommended.adapter = MovieTopRatedAdapter(mainViewModel.movieTopRatedModelLst){
            onItemSelected(it)
        }
    }
    fun onItemSelected(movie:MovieModel){
        Toast.makeText(this,movie.releaseDate, Toast.LENGTH_SHORT).show()
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("id", movie?.id)
        this.startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

}


