package com.irmak.remoteanddbmovieapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.irmak.cleanarcremoteanddb.data.mapper.toMovie
import com.irmak.remoteanddbmovieapp.data.remote.pageNumber
import com.irmak.remoteanddbmovieapp.data.repository.MovieRepository
import com.irmak.remoteanddbmovieapp.domain.Movie

import kotlinx.coroutines.launch

class MovieViewModel(private val repository: MovieRepository) : ViewModel() {

//    val popularMovies: Flow<List<MovieEntity>> = repository.getPopularMovies().flowOn(Dispatchers.IO)

    private val _popularMoviesList = MutableLiveData<List<Movie>>()
    val popularMoviesList: LiveData<List<Movie>> = _popularMoviesList

    init {
        refreshPopularMovies()
    }

        fun refreshPopularMovies() {
            viewModelScope.launch {
                if (isNetworkAvailable == true) {
                    repeat(4){
                        repository.fetchAndSavePopularMovies()
                        pageNumber++
                    }
                }
                val movieEntityList = repository.getAllMovies()
                val movieList = movieEntityList.map { it.toMovie() }
                _popularMoviesList.postValue(movieList)
            }
        }
    }





