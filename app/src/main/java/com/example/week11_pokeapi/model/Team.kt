package com.example.week11_pokeapi.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "teams")
data class Team(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val description: String?,
)

data class TeamWithPokemon(
    @Embedded val team: Team,
    @Relation(
        parentColumn = "id",
        entityColumn = "teamId"
    )
    val pokemons: List<Pokemon>
)
