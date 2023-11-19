package com.example.week11_pokeapi.database

import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.week11_pokeapi.model.Pokemon
import com.example.week11_pokeapi.model.PokemonAbility
import com.example.week11_pokeapi.model.Team

@Database(
    entities = [
                Team::class,
                Pokemon::class,
                PokemonAbility::class,
               ],
    version = 1,
    exportSchema = false
)
//@TypeConverters(Converters::class)
abstract class PokeDatabase: RoomDatabase() {
    abstract fun teamDao(): TeamDao?
    companion object {
        @Volatile
        private var INSTANCE: PokeDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: android.content.Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: getDatabase(context).also { INSTANCE = it }
        }

        fun getDatabase(context: android.content.Context): PokeDatabase? {
            if (INSTANCE == null) {
                synchronized(PokeDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = databaseBuilder(
                            context.applicationContext,
                            PokeDatabase::class.java, "poke_db.db"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }
}