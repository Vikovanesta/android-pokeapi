package com.example.week11_pokeapi.model

data class Stat(
    val id: Int,
    val name: String,
    val gameIndex: String,
    val isBattleOnly: Boolean,
    val affectingMoves: MoveStatAffectSets,
    val affectingNatures: NatureStatAffectSets,
    val characteristics: List<ApiResource>,
    val moveDamageClass: NamedApiResource,
    val names: List<Name>
)

data class MoveStatAffectSets(
    val increase: List<MoveStatAffect>,
    val decrease: List<MoveStatAffect>
)

data class MoveStatAffect(
    val change: Int,
    val move: NamedApiResource
)

data class NatureStatAffectSets(
    val increase: NamedApiResource,
    val decrease: NamedApiResource
)