package com.irmak.cleanarcremoteanddb.data.mapper


import com.irmak.remoteanddbmovieapp.data.local.MovieEntity
import com.irmak.remoteanddbmovieapp.data.remote.MovieDto

fun MovieDto.toMovieEntityList(): List<MovieEntity> {
    return results?.mapNotNull { movieResponse ->
        MovieEntity(
            id = movieResponse.id,
            title = movieResponse.title,
            overview = movieResponse.overview,
            posterPath = movieResponse.poster_path,
            backdropPath = movieResponse.backdrop_path,
            releaseDate = movieResponse.release_date
        )
    } ?: emptyList()
}


fun MovieEntity.toMovie(): com.irmak.remoteanddbmovieapp.domain.Movie {
    return com.irmak.remoteanddbmovieapp.domain.Movie(
        id = id,
        title = title,
        overview = overview,
        poster_path = posterPath,
        backdrop_path = backdropPath,
        release_date = releaseDate
    )
}
