package com.example.week11_pokeapi.model

import android.security.KeyChain
import com.google.gson.annotations.SerializedName

data class PokemonSpecies(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("is_baby")
    val isBaby: Boolean,
    @SerializedName("is_legendary")
    val isLegendary: Boolean,
    @SerializedName("is_mythical")
    val isMythical: Boolean,
    @SerializedName("egg_groups")
    val eggGroups: List<NamedApiResource>,
    @SerializedName("color")
    val color: NamedApiResource,
    @SerializedName("shape")
    val shape: NamedApiResource,
    @SerializedName("evolves_from_species")
    val evolvesFromSpecies: NamedApiResource?,
    @SerializedName("evolution_chain")
    val evolutionChain: ApiResource,
    @SerializedName("habitat")
    val habitat: NamedApiResource,
    @SerializedName("generation")
    val generation: NamedApiResource,
    @SerializedName("flavor_text_entries")
    val flavorTextEntries: List<FlavorText>,
    @SerializedName("genera")
    val genera: List<Genus>,
)

data class FlavorText(
    @SerializedName("flavor_text")
    val flavorText: String,
    @SerializedName("language")
    val language: NamedApiResource,
    @SerializedName("version")
    val version: NamedApiResource
)

data class  Genus(
    @SerializedName("genus")
    val genus: String,
    @SerializedName("language")
    val language: NamedApiResource
)
