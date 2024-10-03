package com.fako.videoexam.retrofit

import com.fako.videoexam.entity.MoviesResponse
import retrofit2.http.GET

interface MovieDao {

    //http://kasimadalan.pe.hu/"
    //"http://kasimadalan.pe.hu/filmler_yeni/tum_filmler.php"

    @GET("filmler_yeni/tum_filmler.php")
    suspend fun bringMovies():MoviesResponse
}