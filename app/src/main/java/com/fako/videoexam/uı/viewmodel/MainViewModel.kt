package com.fako.videoexam.uı.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fako.videoexam.entity.Movies
import com.fako.videoexam.repo.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel:ViewModel() {
    var mrepo = MovieRepository()
    var movieList = MutableLiveData<List<Movies>>()

    init {
        bringMovie()
        bringMovieExtra()
    }

     fun bringMovie(){
         CoroutineScope(Dispatchers.Main).launch {
             movieList.value = mrepo.bringMovie()
         }
     }
     fun bringMovieExtra(){
         CoroutineScope(Dispatchers.Main).launch {
             movieList.value = mrepo.bringMovieExtra()
         }
     }
     fun ara(aramaKelimesi:String){
         CoroutineScope(Dispatchers.Main).launch {
             mrepo.ara(aramaKelimesi)
         }
     }
}