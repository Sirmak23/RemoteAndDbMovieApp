package com.irmak.remoteanddbmovieapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: List<MovieEntity>)

    @Query("SELECT * FROM movie_database")
    fun getMovies(): List<MovieEntity>

    @Query("SELECT * FROM movie_database WHERE id = :id")
    suspend fun getMovieById(id: Int): MovieEntity?

    @Query("DELETE FROM movie_database")
    suspend fun clearAll()
}