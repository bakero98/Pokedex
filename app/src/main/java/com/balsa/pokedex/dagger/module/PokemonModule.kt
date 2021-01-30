package com.balsa.pokedex.dagger.module

import com.balsa.pokedex.R
import com.balsa.pokedex.db.PokemonDatabase
import com.balsa.pokedex.model.PokemonType
import com.balsa.pokedex.repos.PokemonRepository
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


    @Singleton
    @Provides
    fun providePokemonTypes(): List<PokemonType> {
        return listOf(
            PokemonType("Electric", "Electric pokemons", R.drawable.electric),
            PokemonType("Fire", "Fire pokemons", R.drawable.fire),
            PokemonType("Grass", "Grass pokemons", R.drawable.grass),
            PokemonType("Water", "Water pokemons", R.drawable.water),
            PokemonType("Posion", "Poison pokemons", R.drawable.poison),
            PokemonType("Normal", "Normal pokemons", R.drawable.normal)
        )
    }

}