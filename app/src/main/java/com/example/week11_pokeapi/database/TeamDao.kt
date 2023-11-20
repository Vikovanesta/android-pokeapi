package com.example.week11_pokeapi.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.week11_pokeapi.model.Pokemon
import com.example.week11_pokeapi.model.Team
import com.example.week11_pokeapi.model.TeamWithPokemon

@Dao
interface TeamDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(team: Team) : Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertPokemons(vararg pokemon: Pokemon)

    @Update
    fun update(team: Team)

    @Delete
    fun delete(team: Team)

    @get:Query("SELECT * FROM teams ORDER BY id DESC")
    val allTeams: LiveData<List<Team>>

    @get:Query("SELECT * FROM teams ORDER BY id DESC")
    val allTeamsWithPokemon: LiveData<List<TeamWithPokemon>>
}