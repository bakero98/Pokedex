package com.balsa.pokedex.ui.addPokemon

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.balsa.pokedex.db.PokemonRepository
import java.lang.IllegalArgumentException

class AddPokemonViewModelFactory(private val pokemonRepository: PokemonRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AddPokemonViewModel::class.java)) {
            return AddPokemonViewModel(pokemonRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}