package com.balsa.pokedex.db

class PokemonRepository(private val pokemonDAO: PokemonDAO) {

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

}