package com.balsa.pokedex.dagger.module

import android.app.Application
import androidx.room.Room
import com.balsa.pokedex.db.PokemonDAO
import com.balsa.pokedex.db.PokemonDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomDatabaseModule(application: Application) {

    private var pokedexApplication = application
    private lateinit var pokemonDatabase : PokemonDatabase

    @Singleton
    @Provides
    fun providesRoomDatabase(): PokemonDatabase {
        pokemonDatabase = Room.databaseBuilder(pokedexApplication, PokemonDatabase::class.java, "pokemon_data_database")
            .fallbackToDestructiveMigration()
            .build()
        return pokemonDatabase
    }

    @Singleton
    @Provides
    fun providesPokemonDAO(pokemonDatabase: PokemonDatabase): PokemonDAO {
        return pokemonDatabase.pokemonDAO
    }
}