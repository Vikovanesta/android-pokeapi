package com.example.week11_pokeapi.model

data class Type(
    val id: Int,
    val name: String,
    val damageRelations: TypeRelations,
    val pastDamageRelations: TypeRelationsPast,
    val gameIndices: List<GenerationGameIndex>,
    val generation: NamedApiResource,
    val moveDamageClass: NamedApiResource,
    val names: List<Name>,
    val pokemon: List<TypePokemon>,
    val moves: List<NamedApiResource>
)

data class TypePokemon(
    val slot: Int,
    val pokemon: NamedApiResource
)

data class TypeRelations(
    val noDamageTo: List<NamedApiResource>,
    val halfDamageTo: List<NamedApiResource>,
    val doubleDamageTo: List<NamedApiResource>,
    val noDamageFrom: List<NamedApiResource>,
    val halfDamageFrom: List<NamedApiResource>,
    val doubleDamageFrom: List<NamedApiResource>
)

data class TypeRelationsPast(
    val generation: NamedApiResource,
    val damageRelations: TypeRelations
)
