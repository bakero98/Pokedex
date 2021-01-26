package com.balsa.pokedex.dagger.module

import com.balsa.pokedex.db.PokemonDatabase
import com.balsa.pokedex.repos.PokemonRepository
import com.balsa.pokedex.ui.home.HomeFragmentViewModelFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PokemonModule {

    @Singleton
    @Provides
    fun providesPokemonRepository(pokemonDatabase: PokemonDatabase): PokemonRepository {
        return PokemonRepository(pokemonDatabase)
    }

}