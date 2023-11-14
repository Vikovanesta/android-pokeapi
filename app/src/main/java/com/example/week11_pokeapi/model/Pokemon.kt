package com.example.week11_pokeapi.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int,
    @SerializedName("height")
    val height: Int,
    @SerializedName("weight")
    val weight: Int,
    @SerializedName("abilities")
    val abilities: List<PokemonAbility>,
    @SerializedName("species")
    val species: NamedApiResource,
    @SerializedName("types")
    val types: List<PokemonType>,
    @SerializedName("sprites")
    val sprites: PokemonSprites
)

data class PokemonAbility(
    @SerializedName("ability")
    val ability: NamedApiResource,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)

data class PokemonType(
    @SerializedName("slot")
    val slot: Int,
    @SerializedName("type")
    val type: NamedApiResource
)

data class  PokemonSprites(
    @SerializedName("front_default")
    val frontDefault: String?,
    @SerializedName("back_default")
    val backDefault: String?,
    @SerializedName("front_shiny")
    val frontShiny: String?,
    @SerializedName("back_shiny")
    val backShiny: String?,
    @SerializedName("front_female")
    val frontFemale: String?,
    @SerializedName("back_female")
    val backFemale: String?,
    @SerializedName("front_shiny_female")
    val frontShinyFemale: String?,
    @SerializedName("back_shiny_female")
    val backShinyFemale: String?,
    @SerializedName("other")
    val other: PokemonSpritesOther
)

data class PokemonSpritesOther(
    @SerializedName("official-artwork")
    val officialArtwork: PokemonSpritesOfficialArtwork
)

data class PokemonSpritesOfficialArtwork(
    @SerializedName("front_default")
    val frontDefault: String?
)
