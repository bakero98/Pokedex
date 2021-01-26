package com.balsa.pokedex

import android.app.Activity
import android.app.Application
import com.balsa.pokedex.dagger.component.DaggerPokemonComponent
import com.balsa.pokedex.dagger.component.PokemonComponent
import com.balsa.pokedex.dagger.module.RoomDatabaseModule
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.internal.DaggerCollections
import javax.inject.Inject

class PokedexApplication : Application() {

//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {
        lateinit var pokemonComponent: PokemonComponent
    }


    override fun onCreate() {
        super.onCreate()
        initID()
    }

    private fun initID() {
        pokemonComponent = DaggerPokemonComponent
            .builder()
            .roomDatabaseModule(RoomDatabaseModule(this))
            .build()
    }

}