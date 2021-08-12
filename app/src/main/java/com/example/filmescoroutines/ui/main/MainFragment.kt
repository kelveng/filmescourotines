package com.example.filmescoroutines.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.filmescoroutines.R
import com.example.filmescoroutines.databinding.MainFragmentBinding
import com.example.filmescoroutines.ui.main.repositories.FilmesRepository

class MainFragment : Fragment() {
    private lateinit var binding: MainFragmentBinding
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, MainViewModel.MainViewModelFactory(FilmesRepository())).get(MainViewModel::class.java)
       viewModel.filmesLiveData.observe(viewLifecycleOwner, Observer { filmes-> binding.textViewfilme.text = filmes[0].title })
    }

    override fun onResume() {
        super.onResume()

        viewModel.getFilmesCoroutines()
    }

}