package com.balsa.pokedex.ui.home

import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import com.balsa.pokedex.db.PokemonRepository

class HomeFragmentViewModel(private val pokemonRepository: PokemonRepository): ViewModel(), Observable {

    val pokemons = pokemonRepository.pokemons


    override fun addOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        return
    }

    override fun removeOnPropertyChangedCallback(callback: androidx.databinding.Observable.OnPropertyChangedCallback?) {
        return
    }
}