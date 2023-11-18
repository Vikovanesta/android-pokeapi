package com.example.week11_pokeapi.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val order: Int,
    val height: Int,
    val weight: Int,
    val abilities: List<PokemonAbility>,
    val species: NamedApiResource,
    val types: List<PokemonType>,
    val sprites: PokemonSprites
)

data class PokemonAbility(
    val ability: NamedApiResource,
    val isHidden: Boolean,
    val slot: Int
)

data class PokemonType(
    val slot: Int,
    val type: NamedApiResource
)

data class  PokemonSprites(
    val frontDefault: String?,
    val backDefault: String?,
    val frontShiny: String?,
    val backShiny: String?,
    val frontFemale: String?,
    val backFemale: String?,
    val frontShinyFemale: String?,
    val backShinyFemale: String?,
    val other: PokemonSpritesOther
)

data class PokemonSpritesOther(
    @SerializedName("official-artwork")
    val officialArtwork: PokemonSpritesOfficialArtwork
)

data class PokemonSpritesOfficialArtwork(
    val frontDefault: String?
)
