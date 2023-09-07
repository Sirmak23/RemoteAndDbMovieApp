package com.irmak.remoteanddbmovieapp.ui

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import com.irmak.remoteanddbmovieapp.adapter.PopularMovieAdapter
import com.irmak.remoteanddbmovieapp.data.local.MovieDatabase
import com.irmak.remoteanddbmovieapp.data.remote.MovieApi
import com.irmak.remoteanddbmovieapp.data.remote.RetrofitClient
import com.irmak.remoteanddbmovieapp.data.repository.MovieRepository
import com.irmak.remoteanddbmovieapp.databinding.FragmentPopularMovieBinding
import com.irmak.remoteanddbmovieapp.domain.Movie
import kotlinx.coroutines.coroutineScope
import retrofit2.Retrofit
import kotlin.properties.Delegates

var isNetworkAvailable:Boolean?= null

class PopularMovieFragment : Fragment() {
    lateinit var binding: FragmentPopularMovieBinding

    var popList: List<Movie> by Delegates.observable(arrayListOf()){_,_, newValue ->
        if (newValue.isNullOrEmpty().not()){
            adapterPop.setPopularList(ArrayList(newValue))
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPopularMovieBinding.inflate(inflater, container, false)
        val isNetworkAvailableValue = isInternetAvailable(requireContext())
        if (isNetworkAvailableValue == true) {
            isNetworkAvailable = true
        }
        return binding.root
    }



private fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val networkInfo = connectivityManager.activeNetworkInfo
    return networkInfo != null && networkInfo.isConnected
}

    val database:MovieDatabase by lazy {
        MovieDatabase(requireContext())
    }
    private val retrofit: Retrofit by lazy {
        RetrofitClient.getRetrofit()
    }
    private val movieApi: MovieApi by lazy {
        retrofit.create(MovieApi::class.java)
    }
    private val movieRepository: MovieRepository by lazy {
        MovieRepository(movieApi,database.dao())
    }
    private val movieViewModel: MovieViewModel by viewModels {
        MovieViewModelFactory(movieRepository)
    }
    private val adapterPop:PopularMovieAdapter by lazy {
        PopularMovieAdapter()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerview()
        observePop()
        binding.floatingActionButton2.setOnClickListener{
            movieViewModel.refreshPopularMovies()
        }
    }
    fun  initRecyclerview (){
        with(binding){
            popularRecyclerView.apply {
                adapterPop.setPopularList(ArrayList(popList))
                adapter=adapterPop
            }
        }
    }
    fun observePop(){
        movieViewModel.popularMoviesList.observe(viewLifecycleOwner){popList->
            this.popList = popList
        }
    }
}
