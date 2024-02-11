package com.example.mekotlin52.ui.fragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mekotlin52.data.model.AnimeBox
import com.example.mekotlin52.data.repositories.AnimeRepositories
import com.example.mekotlin52.ui.UiState

class AnimeViewModel : ViewModel() {
    private val animeRepositories = AnimeRepositories()
    private val _animeLiveData = MutableLiveData<UiState<List<AnimeBox>>>()
    val animeLiveData : LiveData<UiState<List<AnimeBox>>> = _animeLiveData

    init {
        getAnimeBox()
    }
    fun getAnimeBox(){
        android.os.Handler().postDelayed(
            {
                val animeBox = animeRepositories.getAnimeBox()
                if (animeBox.size <= 10){
                    _animeLiveData.value =
                        UiState(isLoading = false, success = animeBox)
                }else{
                    _animeLiveData.value =
                        UiState(isLoading = false, error = "very large amout of data")
                }
            }, 3000
        )
    }
    fun  addAnimeBox(animeBox: AnimeBox){
        animeRepositories.addAnimeBox(animeBox)
    }

    override fun onCleared() {
        super.onCleared()
    }
}