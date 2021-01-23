package com.balsa.pokedex.ui.detailPokemon

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.balsa.pokedex.databinding.EditPokemonDialogBinding

class EditPokemonDialog(
    private val context: Context,
    private val layoutInflater: LayoutInflater,
    private var pokemonDetailViewModel: PokemonDetailViewModel
) {

    private lateinit var binding : EditPokemonDialogBinding
    private lateinit var dialog : AlertDialog

    fun startEditPokemonDialog() {
        binding = EditPokemonDialogBinding.inflate(layoutInflater)
        val builder = AlertDialog.Builder(context)
        builder.setView(binding.root)
        builder.setCancelable(true)

        dialog = builder.create()
        fillView()

        dialog.show()
    }

    private fun fillView() {
        binding.apply {
            editName.setText(pokemonDetailViewModel.pokemon.value?.name)
            updateButton.setOnClickListener{
                pokemonDetailViewModel.pokemon.value?.name = editName.text.toString()
                pokemonDetailViewModel.updatePokemon()
                dialog.dismiss()
            }
        }

    }
}