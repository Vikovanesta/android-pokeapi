package com.example.week11_pokeapi.model

import com.google.gson.annotations.SerializedName

data class Type(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
)
