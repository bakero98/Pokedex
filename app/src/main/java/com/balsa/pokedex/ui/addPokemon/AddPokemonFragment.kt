package com.balsa.pokedex.ui.addPokemon

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.balsa.pokedex.PokedexApplication
import com.balsa.pokedex.R
import com.balsa.pokedex.databinding.FragmentAddPokemonBinding
import com.balsa.pokedex.db.PokemonDatabase
import com.balsa.pokedex.repos.PokemonRepository
import javax.inject.Inject


class AddPokemonFragment : Fragment() {

    private lateinit var binding: FragmentAddPokemonBinding
    private lateinit var addPokemonViewModel: AddPokemonViewModel

    @Inject
    lateinit var addPokemonViewModelFactory: AddPokemonViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentAddPokemonBinding.inflate(layoutInflater)

        injectDagger()

        addPokemonViewModel = ViewModelProvider(requireActivity(), addPokemonViewModelFactory).get(AddPokemonViewModel::class.java)

        binding.imageInput.setOnClickListener{
            uploadImage()
        }

        binding.addButton.setOnClickListener{
            val selectedId = binding.typesRadioGroup.checkedRadioButtonId
            if(selectedId != -1 && !binding.pokemonNameInput.text.isNullOrEmpty() && addPokemonViewModel.bitmapChanged) {
                var pokemonType = binding.root.findViewById<RadioButton>(selectedId).text.toString()
                addPokemonViewModel.addPokemon(
                    binding.pokemonNameInput.text.toString(),
                    binding.powerInput.value,
                    pokemonType
                )
                clearInputFields()
            }else{
                Toast.makeText(context, "Please fill all Pokemon details", Toast.LENGTH_LONG).show()
            }
        }

        setUpPowerPicker()

        return binding.root
    }

    private fun injectDagger() {
        PokedexApplication.pokemonComponent.inject(this)
    }


    private fun setUpPowerPicker() {
        binding.powerInput.minValue = 0
        binding.powerInput.maxValue = 30
        binding.powerInput.wrapSelectorWheel = true
    }

    private fun uploadImage() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.data != null) {
            setBitmapImage(data.data!!)
        }
    }

    private fun setBitmapImage(data: Uri) {
        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, data)
        addPokemonViewModel.bitmap = bitmap
        addPokemonViewModel.bitmapChanged = true
        binding.imageInput.load(bitmap)
    }

    private fun clearInputFields() {
        binding.apply {
            imageInput.load(R.drawable.ic_add_photo)
            pokemonNameInput.text.clear()
            powerInput.value = 0
            typesRadioGroup.clearCheck()
        }
    }



}