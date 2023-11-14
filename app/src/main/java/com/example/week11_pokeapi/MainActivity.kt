package com.example.week11_pokeapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week11_pokeapi.adapters.ItemPokemonAdapter
import com.example.week11_pokeapi.client.PokeApiClient
import com.example.week11_pokeapi.databinding.ActivityMainBinding
import com.example.week11_pokeapi.model.NamedApiResourceList
import com.example.week11_pokeapi.model.Pokemon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Set adapter recycler view.
        // note: adapter harus diset pas onCreate, gabisa di dalam onResponse
        val pokemonAdapter = ItemPokemonAdapter() {
                pokemon -> Toast.makeText(this@MainActivity, "Yu klik on ${pokemon.name}",
            Toast.LENGTH_SHORT).show()
        }
        binding.rvPokemon.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

        val pokeApiClient = PokeApiClient.getInstance()
        val response = pokeApiClient.getPokemonList(20, 0)

        response.enqueue(object : Callback<NamedApiResourceList> {
            override fun onResponse(
                call: Call<NamedApiResourceList>,
                response: Response<NamedApiResourceList>
            ) {
                if (response.isSuccessful) {
                    val pokemonList = response.body()?.results ?: emptyList()
                    val pokemonIds = pokemonList.map { it.id }

                    // runBlocking: block thread (nunggu semua proses async selesai dlu)
                    runBlocking {
                    pokemonAdapter.setPokemonList(getPokemonModels(pokemonIds))
                    }

                }
            }

            override fun onFailure(call: Call<NamedApiResourceList>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Request pokemon models in parallel
    // suspend: fungsi di suspend dlu sampe proses async selesai
    private suspend fun getPokemonModels(pokemonIds: List<Int>): List<Pokemon> {
        val pokeApiClient = PokeApiClient.getInstance()
        val pokemonModels = mutableListOf<Pokemon>()

        // Execute api request in parallel
        val jobs = pokemonIds.map { id ->
            CoroutineScope(Dispatchers.IO).async {
                try {
                    val response = pokeApiClient.getPokemon(id).execute()
                    response
                } catch (e: Exception) {
                    null
                }
            }
        }

        // Wait for all jobs to finish, then add each model to list
        jobs.awaitAll().forEach { response ->
            response?.let {
                if (it.isSuccessful) {
                    val pokemon = it.body()
                    if (pokemon != null) {
                        pokemonModels.add(pokemon)
                    }
                }
            }
        }

        return pokemonModels
    }
}