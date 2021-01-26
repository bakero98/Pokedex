package com.balsa.pokedex.ui.addPokemon

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balsa.pokedex.repos.PokemonRepository
import com.balsa.pokedex.ui.home.HomeFragmentViewModelFactory
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AddPokemonViewModelFactory @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddPokemonViewModel::class.java)) {
            return AddPokemonViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}