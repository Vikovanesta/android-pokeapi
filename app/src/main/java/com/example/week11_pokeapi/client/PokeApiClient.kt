package com.example.week11_pokeapi.client

import com.example.week11_pokeapi.adapters.ApiResourceAdapter
import com.example.week11_pokeapi.adapters.NamedApiResourceAdapter
import com.example.week11_pokeapi.model.ApiResource
import com.example.week11_pokeapi.model.NamedApiResource
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object PokeApiClient {
    fun getInstance() : PokeApiService {
        val mOkHttpInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkHttpClient = OkHttpClient.Builder()
            .addInterceptor(mOkHttpInterceptor)
            .build()
        val builder = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create(
                GsonBuilder().apply {
                    setPrettyPrinting()
                    setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    // Custom adapter buat ApiResource + NamedApiResource
                    // Buat deserealize dari JSON ke model + inisialisasi model
                    registerTypeAdapter(
                        TypeToken.get(ApiResource::class.java).type,
                        ApiResourceAdapter()
                    )
                    registerTypeAdapter(
                        TypeToken.get(NamedApiResource::class.java).type,
                        NamedApiResourceAdapter()
                    )
                }.create()
            ))
            .client(mOkHttpClient)
            .build()

        return builder.create(PokeApiService::class.java)
    }
}