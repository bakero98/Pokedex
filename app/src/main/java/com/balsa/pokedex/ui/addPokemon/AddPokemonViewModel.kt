package com.balsa.pokedex.ui.addPokemon

import android.graphics.Bitmap
import androidx.databinding.Observable
import androidx.lifecycle.*
import com.balsa.pokedex.model.Pokemon
import com.balsa.pokedex.repos.PokemonRepository
import kotlinx.coroutines.launch

class AddPokemonViewModel(private val pokemonRepository: PokemonRepository) : ViewModel(), Observable {

     var imageUri = ""
     var bitmap: Bitmap ?= null
     var bitmapChanged = false
     var id = MutableLiveData<Long>(0)




    fun addPokemon(name: String, power: Int, type: String){
        bitmap?.let { Pokemon(0, name, power, type, it) }?.let { addNewPokemon(it) }
//        PokemonImageSaver(context).execute(imageUri, id.toString())
    }

    private fun addNewPokemon(pokemon: Pokemon) = viewModelScope.launch {
        if(pokemon.name.isEmpty() || pokemon.power.toString().isEmpty() || pokemon.type.isEmpty()) {

        }else {
            id.value = pokemonRepository.insert(pokemon)
            bitmapChanged = false
        }
    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        return
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {
        return
    }
}