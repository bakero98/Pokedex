package com.balsa.pokedex.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.balsa.pokedex.R
import com.balsa.pokedex.databinding.PokemonItemViewBinding
import com.balsa.pokedex.model.Pokemon

class PokemonRecycleViewAdapter(
    private val pokemons: List<Pokemon>,
    private val clickListener: (Pokemon, ImageView) -> Unit
) :
    RecyclerView.Adapter<MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindings: PokemonItemViewBinding = PokemonItemViewBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(bindings)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.pokemonItemCardView.setOnClickListener{
            clickListener(pokemons[position], holder.binding.pokemonItemImageView)
        }
        holder.bind(pokemons[position])
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }


}

class MyViewHolder(val binding: PokemonItemViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemon: Pokemon) {
        binding.pokemonItemViewName.text = pokemon.name
        binding.pokemonItemViewType.text = pokemon.type
        binding.pokemonItemPower.text = pokemon.power.toString()
        setCardColorByType(pokemon, binding)
        setPokemonImage(pokemon, binding)
//        binding.pokemonItemCardView.setOnClickListener{
//            clickListener(pokemon)
//        }
    }

    private fun setCardColorByType(pokemon: Pokemon, binding: PokemonItemViewBinding){
        when(pokemon.type) {
            "Normal" -> binding.pokemonItemCardView.setCardBackgroundColor(binding.root.resources.getColor(R.color.cardViewNormal))
            "Grass" -> binding.pokemonItemCardView.setCardBackgroundColor(binding.root.resources.getColor(R.color.cardViewGrass))
            "Fire" -> binding.pokemonItemCardView.setCardBackgroundColor(binding.root.resources.getColor(R.color.cardViewFire))
            "Water" -> binding.pokemonItemCardView.setCardBackgroundColor(binding.root.resources.getColor(R.color.cardViewWater))
            "Poison" -> binding.pokemonItemCardView.setCardBackgroundColor(binding.root.resources.getColor(R.color.cardViewPoison))
            "Electric" -> binding.pokemonItemCardView.setCardBackgroundColor(binding.root.resources.getColor(R.color.cardViewElectric))
        }
    }

    private fun setPokemonImage(pokemon: Pokemon, binding: PokemonItemViewBinding) {
        binding.pokemonItemImageView.load(pokemon.image)
        binding.pokemonItemImageView.transitionName = pokemon.id.toString()
    }
}