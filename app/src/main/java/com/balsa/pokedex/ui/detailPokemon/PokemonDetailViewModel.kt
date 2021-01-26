package com.balsa.pokedex.ui.detailPokemon

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.balsa.pokedex.model.Pokemon
import com.balsa.pokedex.repos.PokemonRepository
import kotlinx.coroutines.launch

class PokemonDetailViewModel(private val pokemonRepository: PokemonRepository): ViewModel() {

    var pokemon = MutableLiveData<Pokemon>()

    fun deletePokemon() = viewModelScope.launch {
        pokemon.value?.let { pokemonRepository.delete(it) }
    }

    fun updatePokemon() = viewModelScope.launch {
        pokemon.value?.let { pokemonRepository.update(it) }
        pokemon.value = pokemon.value
    }
}