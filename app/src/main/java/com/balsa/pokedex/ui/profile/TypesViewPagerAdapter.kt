package com.balsa.pokedex.ui.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.balsa.pokedex.R
import com.balsa.pokedex.databinding.ViewPagerCardItemBinding
import com.balsa.pokedex.model.PokemonType
import kotlinx.android.synthetic.main.edit_pokemon_dialog.view.*
import javax.inject.Inject

class TypesViewPagerAdapter (private val pokemonTypeList: List<PokemonType>) :
    RecyclerView.Adapter<ViewPagerViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val bindings: ViewPagerCardItemBinding = ViewPagerCardItemBinding.inflate(layoutInflater, parent, false)

        return ViewPagerViewHolder(bindings)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
        holder.bind(pokemonTypeList[position])
    }

    override fun getItemCount(): Int {
        return pokemonTypeList.size
    }
}

class ViewPagerViewHolder(val binding: ViewPagerCardItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pokemonType: PokemonType) {
        binding.bannerImage.setImageResource(pokemonType.image)
        binding.descriptionTf.text = pokemonType.description
        binding.titleTf.text = pokemonType.name
        setPokemonTypeDescriptionBoxColor(binding.descLayout, pokemonType.name, binding)
    }
}

private fun setPokemonTypeDescriptionBoxColor(view: View, type: String, binding: ViewPagerCardItemBinding) {
    when(type) {
        "Electric" -> view.setBackgroundColor(binding.root.context.resources.getColor(R.color.cardViewElectric))
        "Fire" -> view.setBackgroundColor(binding.root.context.resources.getColor(R.color.cardViewFire))
        "Posion" -> view.setBackgroundColor(binding.root.context.resources.getColor(R.color.cardViewPoison))
        "Water" -> view.setBackgroundColor(binding.root.context.resources.getColor(R.color.cardViewWater))
        "Grass" -> view.setBackgroundColor(binding.root.context.resources.getColor(R.color.cardViewGrass))
        else -> { view.setBackgroundColor(binding.root.context.resources.getColor(R.color.cardViewNormal)) }
    }
}