package com.balsa.pokedex.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.balsa.pokedex.util.Converters

@Database(entities = [Pokemon::class], version = 1)
@TypeConverters(Converters::class)
abstract class PokemonDatabase : RoomDatabase() {

    abstract val pokemonDAO : PokemonDAO

    companion object{

        @Volatile
        private var INSTANCE : PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PokemonDatabase::class.java,
                        "pokemon_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}