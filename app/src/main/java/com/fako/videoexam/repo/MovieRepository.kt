package com.fako.videoexam.repo

import com.fako.videoexam.datasource.MovieDataSource
import com.fako.videoexam.entity.Movies

class MovieRepository {
        var mds = MovieDataSource()
    suspend fun bringMovie():List<Movies> =mds.bringMovie()
    suspend fun bringMovieExtra():List<Movies> = mds.bringMovieExtra()
    suspend fun ara(aramaKelimesi:String)= mds.ara(aramaKelimesi)
}