package com.example.mekotlin52.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mekotlin52.data.model.AnimeBox
import com.example.mekotlin52.databinding.ItemBinding

class AnimeAdapter : RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>() {
    private var animeList = mutableListOf<AnimeBox>()


    fun setAnimeBox(AnimeList: List<AnimeBox>?) {
        this.animeList = animeList?.toMutableList()!!
        notifyDataSetChanged()
    }

    inner class AnimeViewHolder(private val binding: ItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(animeBox: AnimeBox) {
            binding.ivItemAnime.setImageResource(animeBox.image)
            binding.tvItemAnime.text = animeBox.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimeViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnimeViewHolder(binding)
    }

    override fun getItemCount() = animeList.size

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        holder.onBind(animeList[position])
    }
}

