package com.example.games

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import coil.Coil
import coil.ImageLoader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit(application: Application): AndroidViewModel(application) {

    private val apiService = Retrofit.Builder()
        .baseUrl("https://www.freetogame.com/api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ApiService::class.java)

    private  val image = ImageLoader.Builder(application)
        .crossfade(true)
        .build()

    init {
        Coil.setImageLoader(image)
        fetGames()
    }


    private  val _games = MutableStateFlow<List<Games>>(emptyList())
    val games:StateFlow<List<Games>> = _games
    init {

fetGames()
    }

    fun fetGames(){

        viewModelScope.launch(Dispatchers.IO)
        {
            try {
                val response = apiService.getGames()
                _games.value = response

            }catch (e:Exception){

            }
        }

    }
}