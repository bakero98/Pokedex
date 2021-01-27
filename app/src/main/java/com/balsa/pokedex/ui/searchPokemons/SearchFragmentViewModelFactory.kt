package com.balsa.pokedex.ui.searchPokemons

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balsa.pokedex.repos.PokemonRepository
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchFragmentViewModelFactory @Inject constructor(private val pokemonRepository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SearchFragmentViewModel::class.java)) {
            return SearchFragmentViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}