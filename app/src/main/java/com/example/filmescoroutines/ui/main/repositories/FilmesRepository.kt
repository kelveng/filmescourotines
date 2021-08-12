package com.example.filmescoroutines.ui.main.repositories

import com.example.filmescoroutines.ui.main.models.Filme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class FilmesRepository {
    fun getFilmesThread(callback:(filmes:List<Filme>) -> Unit){
        Thread(
            Runnable {
                Thread.sleep(3000)
                callback.invoke(
                    listOf(
                        Filme(id = 1,title = "Teste 1"),
                        Filme(id = 2,title = "Teste 2")
                    )
                )
            }
        ).start()
    }

    suspend fun getFilmesCoroutines(): List<Filme>{
        return withContext(Dispatchers.Default,{
            delay(3000)
            listOf(
                Filme(id = 1,title = "Teste 1"),
                Filme(id = 2,title = "Teste 2")
            )
        })
    }
}