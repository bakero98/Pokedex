package com.balsa.pokedex.ui.detailPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balsa.pokedex.db.Pokemon
import com.balsa.pokedex.db.PokemonRepository
import java.lang.IllegalArgumentException

class PokemonDetailViewModelFactory(private val pokemonRepository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}