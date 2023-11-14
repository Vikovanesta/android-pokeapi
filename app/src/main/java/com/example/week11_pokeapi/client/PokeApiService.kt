package com.example.week11_pokeapi.client

import com.example.week11_pokeapi.model.NamedApiResourceList
import com.example.week11_pokeapi.model.Pokemon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/")
    fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Call<NamedApiResourceList>

    @GET("pokemon/{id}")
    fun getPokemon(
        @Path("id") id: Int
    ): Call<Pokemon>
}