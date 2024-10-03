package com.fako.videoexam.datasource

import android.util.Log
import com.fako.videoexam.entity.Movies
import com.fako.videoexam.retrofit.ApiUtils
import com.fako.videoexam.retrofit.MovieDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MovieDataSource {
          var md :MovieDao = ApiUtils.getMovieDao()

   suspend fun bringMovie():List<Movies> = withContext(Dispatchers.IO) {

        return@withContext md.bringMovies().filmler
    }
    suspend fun bringMovieExtra():List<Movies> = withContext(Dispatchers.IO) {

        return@withContext md.bringMovies().filmler
    }

   suspend fun ara(aramaKelimesi:String) {
        Log.e("tag",aramaKelimesi)
    }
}