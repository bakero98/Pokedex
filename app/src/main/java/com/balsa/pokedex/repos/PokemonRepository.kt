package com.balsa.pokedex.repos

import android.util.Log
import androidx.lifecycle.LiveData
import com.balsa.pokedex.db.PokemonDAO
import com.balsa.pokedex.db.PokemonDatabase
import com.balsa.pokedex.model.Pokemon

class PokemonRepository(pokemonDatabase: PokemonDatabase) {

    val pokemonDAO : PokemonDAO = pokemonDatabase.pokemonDAO
    val pokemons = pokemonDAO.getAllPokemons()

    suspend fun insert(pokemon: Pokemon) : Long {
        return pokemonDAO.insertPokemon(pokemon)
    }

    suspend fun update(pokemon: Pokemon) {
        pokemonDAO.updatePokemon(pokemon)
    }

    suspend fun delete(pokemon: Pokemon) {
        pokemonDAO.deletePokemon(pokemon)
    }

    fun getPokemonsByQuery(query: String) : LiveData<List<Pokemon>> {
        return pokemonDAO.getPokemonsByQuery(query)
    }

}