package com.balsa.pokedex.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PokemonDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: Pokemon) : Long

    @Update
    suspend fun updatePokemon(pokemon: Pokemon)

    @Delete
    suspend fun deletePokemon(pokemon: Pokemon)

    @Query("SELECT * FROM pokemon_data_table")
    fun getAllPokemons():LiveData<List<Pokemon>>
}