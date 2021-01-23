package com.balsa.pokedex.ui.detailPokemon

import android.os.Bundle
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import com.balsa.pokedex.R
import com.balsa.pokedex.databinding.FragmentPokemonDetailBinding
import com.balsa.pokedex.db.Pokemon
import com.balsa.pokedex.db.PokemonDatabase
import com.balsa.pokedex.db.PokemonRepository


class PokemonDetailFragment : Fragment() {

    private lateinit var binding: FragmentPokemonDetailBinding
    private lateinit var pokemonDetailViewModel: PokemonDetailViewModel

    private val pokemonArgs: PokemonDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPokemonDetailBinding.inflate(layoutInflater)
        setUpViewModel()
        addObservers()

        return binding.root
    }

    private fun setUpViewModel() {
        val dao = PokemonDatabase.getInstance(requireContext()).pokemonDAO
        val repository = PokemonRepository(dao)
        val factory = PokemonDetailViewModelFactory(repository)
        pokemonDetailViewModel = ViewModelProvider(requireActivity(), factory).get(PokemonDetailViewModel::class.java)
    }

    private fun addObservers() {
        pokemonDetailViewModel.pokemon.observe(viewLifecycleOwner, {
            setDetails()
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonDetailViewModel.pokemon.value = pokemonArgs.pokemon
    }

    private fun setDetails() {
        binding.apply {
            PokemonImageView.load(pokemonDetailViewModel.pokemon.value?.image)
            PokemonNameView.text = pokemonDetailViewModel.pokemon.value?.name
            pokemonName.text = pokemonDetailViewModel.pokemon.value?.name
            pokemonType.text = pokemonDetailViewModel.pokemon.value?.type
            pokemonPower.text = pokemonDetailViewModel.pokemon.value?.power.toString()
            deletePokemonButton.setOnClickListener{
                pokemonDetailViewModel.deletePokemon()
                Navigation.findNavController(binding.root).navigate(PokemonDetailFragmentDirections.actionPokemonDetailFragmentToMiHome())
            }
            editPokemon.setOnClickListener{
                val editPokemonDialog = EditPokemonDialog(binding.root.context, layoutInflater, pokemonDetailViewModel)
                editPokemonDialog.startEditPokemonDialog()
            }
        }
        pokemonDetailViewModel.pokemon.value?.let { setColorByType(it, binding.pokemonLinearTopView) }
    }

    private fun setColorByType(pokemon: Pokemon, view: LinearLayout){
        when(pokemon.type) {
            "Normal" -> view.setBackgroundColor(binding.root.resources.getColor(R.color.cardViewNormal))
            "Grass" -> view.setBackgroundColor(binding.root.resources.getColor(R.color.cardViewGrass))
            "Fire" -> view.setBackgroundColor(binding.root.resources.getColor(R.color.cardViewFire))
            "Water" -> view.setBackgroundColor(binding.root.resources.getColor(R.color.cardViewWater))
            "Poison" -> view.setBackgroundColor(binding.root.resources.getColor(R.color.cardViewPoison))
            "Electric" -> view.setBackgroundColor(binding.root.resources.getColor(R.color.cardViewElectric))
        }
    }

}