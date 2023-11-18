package com.example.week11_pokeapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week11_pokeapi.adapters.ItemPokemonAdapter
import com.example.week11_pokeapi.client.PokeApiClient
import com.example.week11_pokeapi.databinding.FragmentPokedexBinding
import com.example.week11_pokeapi.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PokedexFragment : Fragment() {
    private var _binding: FragmentPokedexBinding? = null
    private val binding get() = _binding!!
    private val pokemonAdapter = ItemPokemonAdapter {
            pokemon -> Toast.makeText(requireContext(), "Yu klik on ${pokemon.name}",
        Toast.LENGTH_SHORT).show()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPokedexBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()


        val pokeApiClient = PokeApiClient.getInstance()

        lifecycleScope.launch(Dispatchers.IO) {
            val pokemonList = pokeApiClient.getPokemonList(2, 150)
            if (pokemonList.isSuccessful) {
                val pokemonModels = getPokemonModels(pokemonList.body()!!.results.map { it.id })
                withContext(Dispatchers.Main) {
                    pokemonAdapter.setPokemonList(pokemonModels)
                }
            }
        }
    }

    private suspend fun getPokemonModels(pokemonIds: List<Int>): List<Pokemon> {
        val pokeApiClient = PokeApiClient.getInstance()

        return coroutineScope {
            val pokemonDeferred = pokemonIds.map { id ->
                async(Dispatchers.IO) {
                    try {
                        val pokemon = pokeApiClient.getPokemon(id)
                        if (pokemon.isSuccessful) {
                            return@async pokemon.body()!!
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                    return@async null
                }
            }

            val pokemonList = pokemonDeferred.awaitAll().filterNotNull()
            pokemonList
        }
    }


    private fun setupRecyclerView() {
        binding.rvPokemon.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(activity?.applicationContext, LinearLayoutManager.VERTICAL, false)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            PokedexFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}