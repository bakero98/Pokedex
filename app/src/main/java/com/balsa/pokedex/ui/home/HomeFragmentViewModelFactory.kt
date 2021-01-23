package com.balsa.pokedex.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balsa.pokedex.db.PokemonRepository
import com.balsa.pokedex.ui.addPokemon.AddPokemonViewModel
import java.lang.IllegalArgumentException


class HomeFragmentViewModelFactory(private val pokemonRepository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeFragmentViewModel::class.java)) {
            return HomeFragmentViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}