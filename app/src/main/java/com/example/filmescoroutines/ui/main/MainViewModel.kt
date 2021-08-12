package com.example.filmescoroutines.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmescoroutines.ui.main.models.Filme
import com.example.filmescoroutines.ui.main.repositories.FilmesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val repository: FilmesRepository) : ViewModel() {
    val filmesLiveData = MutableLiveData<List<Filme>>()

    fun getFilmesThread(){
        repository.getFilmesThread { filmes -> filmesLiveData.postValue(filmes)  }
    }

    fun getFilmesCoroutines(){
        CoroutineScope(Dispatchers.Main).launch {
            val filmes =
                withContext(Dispatchers.Default) { repository.getFilmesCoroutines() }
            filmesLiveData.value = filmes
        }
    }

    class MainViewModelFactory(
        private val repository: FilmesRepository
    ):ViewModelProvider.Factory{
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return MainViewModel(repository) as T
        }

    }
}
