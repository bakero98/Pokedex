package com.balsa.pokedex.dagger.component

import com.balsa.pokedex.MainActivity
import com.balsa.pokedex.dagger.module.PokemonModule
import com.balsa.pokedex.dagger.module.RoomDatabaseModule
import com.balsa.pokedex.ui.addPokemon.AddPokemonFragment
import com.balsa.pokedex.ui.detailPokemon.PokemonDetailFragment
import com.balsa.pokedex.ui.home.HomeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [PokemonModule::class, RoomDatabaseModule::class])
interface PokemonComponent {
    fun inject(addPokemonFragment: AddPokemonFragment)
    fun inject(pokemonDetailFragment: PokemonDetailFragment)
    fun inject(homeFragment: HomeFragment)
}