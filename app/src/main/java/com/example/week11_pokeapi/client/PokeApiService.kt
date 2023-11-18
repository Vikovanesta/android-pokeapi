package com.example.week11_pokeapi.client

import com.example.week11_pokeapi.model.NamedApiResourceList
import com.example.week11_pokeapi.model.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ): Response<NamedApiResourceList>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): Response<Pokemon>
}