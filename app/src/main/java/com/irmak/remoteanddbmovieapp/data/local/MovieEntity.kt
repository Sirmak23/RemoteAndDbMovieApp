package com.irmak.remoteanddbmovieapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "movie_database")
data class MovieEntity(
    @PrimaryKey
    val id: Int? = null,
    val posterPath: String? = null,
    val title: String? = null,
    val overview: String? = null,
    val backdropPath: String? = null ,
    val releaseDate: String? = null,

    //    val backdropPath: String? = null,
//    val adult: Boolean? = null,
//    val releaseDate: String? = null,
//    val genreIds: List<Int>? = null,
//    val originalTitle: String? = null,
//    val originalLanguage: String? = null,
//    val popularity: Number? = null,
//    val voteCount: Int? = null,
//    val video: Boolean? = null,
//    val voteAverage: Number? = null,
)

