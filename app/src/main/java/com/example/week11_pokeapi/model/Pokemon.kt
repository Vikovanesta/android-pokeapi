package com.example.week11_pokeapi.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    val id: Int,
    val name: String,
    val baseExperience: Int,
    val order: Int,
    val height: Int,
    val weight: Int,
    val locationAreaEncounters: String,
    val isDefault: Boolean,
    val abilities: List<PokemonAbility>,
    val forms: List<NamedApiResource>,
    val gameIndices: List<VersionGameIndex>,
    val heldItems: List<PokemonHeldItem>,
    val moves: List<PokemonMove>,
    val pastTypes: List<PokemonTypePast>,
    val sprites: PokemonSprites,
    val species: NamedApiResource,
    val stats: List<PokemonStat>,
    val types: List<PokemonType>,
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

data class PokemonTypePast(
    val generation: NamedApiResource,
    val types: List<PokemonType>
)

data class PokemonHeldItem(
    val item: NamedApiResource,
    val versionDetails: List<PokemonHeldItemVersion>
)

data class PokemonHeldItemVersion(
    val version: NamedApiResource,
    val rarity: Int
)

data class PokemonMove(
    val move: NamedApiResource,
    val versionGroupDetails: List<PokemonMoveVersion>
)

data class PokemonMoveVersion(
    val moveLearnMethod: NamedApiResource,
    val versionGroup: NamedApiResource,
    val levelLearnedAt: Int
)

data class PokemonStat(
    val stat: NamedApiResource,
    val effort: Int,
    val baseStat: Int
)

data class  PokemonSprites(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?,
    val other: PokemonSpritesOther
)

data class PokemonSpritesOther(
    @SerializedName("official-artwork")
    val officialArtwork: PokemonSpritesOfficialArtwork,
    val dreamWorld: PokemonSpritesDreamWorld,
    val home: PokemonSpritesHome,
    val versions: PokemonSpritesVersions
)

data class PokemonSpritesOfficialArtwork(
    val frontDefault: String?,
    val frontShiny: String?
)

data class PokemonSpritesDreamWorld(
    val frontDefault: String?,
    val frontFemale: String?
)

data class PokemonSpritesHome(
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesVersions(
    @SerializedName("generation-i")
    val generationI: PokemonSpritesGenerationI,
    @SerializedName("generation-ii")
    val generationII: PokemonSpritesGenerationII,
    @SerializedName("generation-iii")
    val generationIII: PokemonSpritesGenerationIII,
    @SerializedName("generation-iv")
    val generationIV: PokemonSpritesGenerationIV,
    @SerializedName("generation-v")
    val generationV: PokemonSpritesGenerationV,
    @SerializedName("generation-vi")
    val generationVI: PokemonSpritesGenerationVI,
    @SerializedName("generation-vii")
    val generationVII: PokemonSpritesGenerationVII,
    @SerializedName("generation-viii")
    val generationVIII: PokemonSpritesGenerationVIII
)

data class PokemonSpritesGenerationI(
    @SerializedName("red-blue")
    val redBlue: PokemonSpritesRBY,
    val yellow: PokemonSpritesRBY
)

data class PokemonSpritesRBY(
    val backDefault: String?,
    val backGray: String?,
    val backTransparent: String?,
    val frontDefault: String?,
    val frontGray: String?,
    val frontTransparent: String?
)

data class PokemonSpritesGenerationII(
    val crystal: PokemonSpritesCrystal,
    val gold: PokemonSpritesGoldSilver,
    val silver: PokemonSpritesGoldSilver
)

data class PokemonSpritesCrystal(
    val backDefault: String?,
    val backShiny: String?,
    val backShinyTransparent: String?,
    val backTransparent: String?,
    val frontDefault: String?,
    val frontShiny: String?,
    val frontShinyTransparent: String?,
    val frontTransparent: String?
)

data class PokemonSpritesGoldSilver(
    val backDefault: String?,
    val backShiny: String?,
    val frontDefault: String?,
    val frontShiny: String?,
    val frontTransparent: String?
)

data class PokemonSpritesGenerationIII(
    val emerald: PokemonSpritesEmerald,
    @SerializedName("firered-leafgreen")
    val fireredLeafgreen: PokemonSpritesFRLGRS,
    @SerializedName("ruby-sapphire")
    val rubySapphire: PokemonSpritesFRLGRS
)

data class PokemonSpritesEmerald(
    val frontDefault: String?,
    val frontShiny: String?
)

data class PokemonSpritesFRLGRS(
    val backDefault: String?,
    val backShiny: String?,
    val frontDefault: String?,
    val frontShiny: String?
)

data class PokemonSpritesGenerationIV(
    @SerializedName("diamond-pearl")
    val diamondPearl: PokemonSpritesDPP,
    @SerializedName("heartgold-soulsilver")
    val heartgoldSoulsilver: PokemonSpritesHGSS,
    val platinum: PokemonSpritesDPP
)

data class PokemonSpritesDPP(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesHGSS(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesGenerationV(
    @SerializedName("black-white")
    val blackWhite: PokemonSpritesBW
)

data class PokemonSpritesBW(
    val animated: PokemonSpritesBWAnimated,
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesBWAnimated(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesGenerationVI(
    @SerializedName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire: PokemonSpritesORAS,
    @SerializedName("x-y")
    val xY: PokemonSpritesXY
)

data class PokemonSpritesORAS(
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesXY(
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesGenerationVII(
    val icons: PokemonSpritesIcons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon: PokemonSpritesUSUM
)

data class PokemonSpritesIcons(
    val frontDefault: String?,
    val frontFemale: String?
)

data class PokemonSpritesUSUM(
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?
)

data class PokemonSpritesGenerationVIII(
    val icons: PokemonSpritesIcons,
    val frontDefault: String?,
    val frontFemale: String?,
)
