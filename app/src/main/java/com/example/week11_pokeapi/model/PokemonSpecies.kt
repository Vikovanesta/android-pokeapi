package com.example.week11_pokeapi.model

data class PokemonSpecies(
    val id: Int,
    val name: String,
    val order: Int,
    val isBaby: Boolean,
    val isLegendary: Boolean,
    val isMythical: Boolean,
    val eggGroups: List<NamedApiResource>,
    val color: NamedApiResource,
    val shape: NamedApiResource,
    val evolvesFromSpecies: NamedApiResource?,
    val evolutionChain: ApiResource,
    val habitat: NamedApiResource,
    val generation: NamedApiResource,
    val flavorTextEntries: List<FlavorText>,
    val genera: List<Genus>,
)

data class FlavorText(
    val flavorText: String,
    val language: NamedApiResource,
    val version: NamedApiResource
)

data class  Genus(
    val genus: String,
    val language: NamedApiResource
)
