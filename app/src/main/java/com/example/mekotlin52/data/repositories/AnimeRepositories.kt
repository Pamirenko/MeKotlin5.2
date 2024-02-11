package com.example.mekotlin52.data.repositories
import com.example.mekotlin52.R
import com.example.mekotlin52.data.model.AnimeBox

class AnimeRepositories {
    val players = mutableListOf(
            AnimeBox(R.drawable.img1 , "Hello every one")
    )


    fun getAnimeBox() = players
    
    fun addAnimeBox(animeBox: AnimeBox){
        players.add(animeBox)
    }
}