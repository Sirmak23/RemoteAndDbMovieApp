package com.irmak.remoteanddbmovieapp.data.remote

import retrofit2.http.GET
import retrofit2.http.Query
var pageNumber:Int = 1
interface MovieApi {

//    @GET("movie/popular")
//    suspend fun getPopularList(
//        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
//        @Query("language") language: String = "tr",
//        @Query("page") pageNo: Int
//    ): List<MovieDto>

    @GET("movie/popular")
    suspend fun getPopularList(
        @Query("api_key") apiKey: String = "5f73da10797f33e35dff709965fdc85a",
        @Query("language") language: String = "tr",
        @Query("page") pageNo: Int = pageNumber
    ): MovieDto


    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}