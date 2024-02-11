package com.example.mekotlin52.ui.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mekotlin52.R
import com.example.mekotlin52.data.repositories.AnimeRepositories
import com.example.mekotlin52.databinding.FragmentAnimeBinding
import com.example.mekotlin52.ui.adapter.AnimeAdapter


class AnimeFragment : Fragment() {
    private var _binding : FragmentAnimeBinding? = null
    private val binding : FragmentAnimeBinding get()= _binding!!
    private val animeAdapter = AnimeAdapter()
    private val viewModel by activityViewModels<AnimeViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAnimeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        toGoSecondFragment()
        subscribeToBoxes()
    }

    private fun initialize() = with(binding){
        val repositories = AnimeRepositories()
        rvAnime.adapter = animeAdapter
        animeAdapter.setAnimeBox(repositories.players)
    }

    private fun toGoSecondFragment() = with(binding){
        addButton.setOnClickListener{
            Handler().postDelayed(
                {
                    progressBar.isInvisible = false
                    findNavController().navigate(R.id.action_charateFragment_to_addAnime)
                },3000
            )
        }
    }
    private fun subscribeToBoxes(){
        viewModel.animeLiveData.observe(viewLifecycleOwner){ UiState->
            UiState?.let{
                binding.progressBar.isVisible = it.isLoading

                if(it.success != null){
                    animeAdapter.setAnimeBox(it.success)
                }else{
                    Toast.makeText(requireContext(),it.error , Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}