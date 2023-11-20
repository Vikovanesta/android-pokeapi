package com.example.week11_pokeapi.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Ignore
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.google.gson.annotations.SerializedName

@Entity(
    tableName = "pokemons",
    foreignKeys = [ForeignKey(
        entity = Team::class,
        parentColumns = ["id"],
        childColumns = ["teamId"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class Pokemon(
    @PrimaryKey val id: Int,
    @ColumnInfo(index = true) val teamId: Int,
    val name: String,
    val baseExperience: Int,
    val order: Int,
    val height: Int,
    val weight: Int,
    val locationAreaEncounters: String,
    val isDefault: Boolean,
    @Ignore val abilities: List<PokemonAbility>,
    @Ignore val forms: List<NamedApiResource>,
    @Ignore val gameIndices: List<VersionGameIndex>,
    @Ignore val heldItems: List<PokemonHeldItem>,
    @Ignore val moves: List<PokemonMove>,
    @Ignore val pastTypes: List<PokemonTypePast>,
    @Embedded(prefix = "sprites") val sprites: PokemonSprites,
    @Embedded(prefix = "species") val species: NamedApiResource,
    @Ignore val stats: List<PokemonStat>,
    @Ignore val types: List<PokemonType>,
) {
    constructor(
        id: Int,
        teamId: Int,
        name: String,
        baseExperience: Int,
        order: Int,
        height: Int,
        weight: Int,
        locationAreaEncounters: String,
        isDefault: Boolean,
        sprites: PokemonSprites,
        species: NamedApiResource
    ) : this(
        id,
        teamId,
        name,
        baseExperience,
        order,
        height,
        weight,
        locationAreaEncounters,
        isDefault,
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        emptyList(),
        sprites,
        species,
        emptyList(),
        emptyList()
    )
}

@Entity(tableName = "pokemon_abilities")
data class PokemonAbility(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val pokemonId: Int,
    @Embedded val ability: NamedApiResource,
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
    @Embedded("other") val other: PokemonSpritesOther
)

data class PokemonSpritesOther(
    @SerializedName("official-artwork")
    @Embedded(prefix = "official_artwork") val officialArtwork: PokemonSpritesOfficialArtwork,
    @Embedded(prefix = "dream_world") val dreamWorld: PokemonSpritesDreamWorld,
    @Embedded(prefix = "home") val home: PokemonSpritesHome,
    @Embedded(prefix = "versions") val versions: PokemonSpritesVersions
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
    @SerializedName("generation-i") @Embedded(prefix = "generation_I")
    val generationI: PokemonSpritesGenerationI,
    @SerializedName("generation-ii") @Embedded(prefix = "generation_II")
    val generationII: PokemonSpritesGenerationII,
    @SerializedName("generation-iii") @Embedded(prefix = "generation_III")
    val generationIII: PokemonSpritesGenerationIII,
    @SerializedName("generation-iv") @Embedded(prefix = "generation_IV")
    val generationIV: PokemonSpritesGenerationIV,
    @SerializedName("generation-v") @Embedded(prefix = "generation_V")
    val generationV: PokemonSpritesGenerationV,
    @SerializedName("generation-vi") @Embedded(prefix = "generation_VI")
    val generationVI: PokemonSpritesGenerationVI,
    @SerializedName("generation-vii") @Embedded(prefix = "generation_VII")
    val generationVII: PokemonSpritesGenerationVII,
    @SerializedName("generation-viii") @Embedded(prefix = "generation_VIII")
    val generationVIII: PokemonSpritesGenerationVIII
)

data class PokemonSpritesGenerationI(
    @SerializedName("red-blue") @Embedded(prefix = "red_blue")
    val redBlue: PokemonSpritesRBY,
    @Embedded(prefix = "yellow") val yellow: PokemonSpritesRBY
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
    @Embedded(prefix = "crystal") val crystal: PokemonSpritesCrystal,
    @Embedded(prefix = "gold") val gold: PokemonSpritesGoldSilver,
    @Embedded(prefix = "silver") val silver: PokemonSpritesGoldSilver
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
    @Embedded(prefix = "emerald") val emerald: PokemonSpritesEmerald,
    @SerializedName("firered-leafgreen") @Embedded(prefix = "firered_leafgreen")
    val fireredLeafgreen: PokemonSpritesFRLGRS,
    @SerializedName("ruby-sapphire") @Embedded(prefix = "ruby_sapphire")
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
    @SerializedName("diamond-pearl") @Embedded(prefix = "diamond_pearl")
    val diamondPearl: PokemonSpritesDPP,
    @SerializedName("heartgold-soulsilver") @Embedded(prefix = "heartgold_soulsilver")
    val heartgoldSoulsilver: PokemonSpritesHGSS,
    @Embedded(prefix = "platinum") val platinum: PokemonSpritesDPP
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
    @SerializedName("black-white") @Embedded(prefix = "black_white")
    val blackWhite: PokemonSpritesBW
)

data class PokemonSpritesBW(
    @Embedded(prefix = "animated") val animated: PokemonSpritesBWAnimated,
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
    @SerializedName("omegaruby-alphasapphire") @Embedded(prefix = "omegaruby_alphasapphire")
    val omegarubyAlphasapphire: PokemonSpritesORAS,
    @SerializedName("x-y") @Embedded(prefix = "x_y")
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
    @Embedded(prefix = "icons") val icons: PokemonSpritesIcons,
    @SerializedName("ultra-sun-ultra-moon") @Embedded(prefix = "ultra_sun_ultra_moon")
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
    @Embedded(prefix = "icons") val icons: PokemonSpritesIcons,
    val frontDefault: String?,
    val frontFemale: String?,
)

data class PokemonWithDetails(
    @Embedded val pokemon: Pokemon,
    @Relation(
        parentColumn = "id",
        entityColumn = "pokemonId"
    )
    val abilities: List<PokemonAbility>,
)
