package com.irmak.remoteanddbmovieapp.data.repository

import com.irmak.cleanarcremoteanddb.data.mapper.toMovieEntityList
import com.irmak.remoteanddbmovieapp.data.local.MovieDao
import com.irmak.remoteanddbmovieapp.data.local.MovieEntity
import com.irmak.remoteanddbmovieapp.data.remote.MovieApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


    class MovieRepository(private val movieApi: MovieApi, private val movieDao: MovieDao) {

        suspend fun fetchAndSavePopularMovies() {
                val movieDtoList = movieApi.getPopularList()
                val movieEntityList = movieDtoList.toMovieEntityList()
                movieDao.insertMovie(movieEntityList)
        }

        suspend fun getAllMovies(): List<MovieEntity> {
            return withContext(Dispatchers.IO) {
                movieDao.getMovies()
            }
        }
    }




//suspend fun fetchAndSavePopularMovies() {
//        val movieDto = movieApi.getPopularList(pageNo = 1)
//        val movieEntities = movieDto.results?.map { movieResponse ->
//            MovieEntity(
//                id = movieResponse.id,
//                title = movieResponse.title,
//                overview = movieResponse.overview,
//                posterPath = movieResponse.poster_path
//                // Diğer gerekli alanlar...
//            )
//        }
//        movieEntities?.let {
//            movieDao.insertMovie(it)
//        }
//    }