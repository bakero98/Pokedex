package com.balsa.pokedex.ui.searchPokemons

import androidx.databinding.Observable
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.balsa.pokedex.repos.PokemonRepository

class SearchFragmentViewModel(private val pokemonRepository: PokemonRepository): ViewModel(), Observable {

    val searchQuery = MutableLiveData(DEFAULT_QUERY)

    var pokemons = searchQuery.switchMap {
            pokemonRepository.getPokemonsByQuery(searchQuery.value!!)
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        return
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        return
    }

    companion object {
        private const val DEFAULT_QUERY = ""
    }
}