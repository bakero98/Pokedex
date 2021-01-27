package com.balsa.pokedex.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.balsa.pokedex.model.Pokemon

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

    @Query("SELECT * FROM pokemon_data_table WHERE UPPER(pokemon_name) LIKE UPPER('%' || :query || '%')")
    fun getPokemonsByQuery(query: String):LiveData<List<Pokemon>>
}