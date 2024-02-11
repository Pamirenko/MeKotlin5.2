package com.example.mekotlin52.ui.fragment

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mekotlin52.R
import com.example.mekotlin52.data.model.AnimeBox
import com.example.mekotlin52.databinding.FragmentAddRegisterBinding


class AddAnimeFragment : Fragment() {
    private var _binding : FragmentAddRegisterBinding? = null
    private val binding : FragmentAddRegisterBinding get() = _binding!!
    private val viewModel by activityViewModels<AnimeViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddRegisterBinding.inflate(layoutInflater, container ,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    private fun setupListenners() = with(binding){
        saveButton.setOnClickListener {
            progressBar.isVisible = true
            Handler().postDelayed({
                progressBar.isVisible = false
                viewModel.addAnimeBox(
                    AnimeBox(
                        image = R.drawable.img1,
                        etName.text.toString().trim()
                    )
                )
                findNavController().navigateUp()
            },3000)
            viewModel.getAnimeBox(

            )
        }
    }
}