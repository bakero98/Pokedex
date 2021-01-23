package com.balsa.pokedex.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.balsa.pokedex.databinding.FragmentHomeBinding
import com.balsa.pokedex.db.Pokemon
import com.balsa.pokedex.db.PokemonDatabase
import com.balsa.pokedex.db.PokemonRepository


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeFragmentViewModel: HomeFragmentViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)

        val pokemonDAO = PokemonDatabase.getInstance(requireContext()).pokemonDAO
        val pokemonRepository = PokemonRepository(pokemonDAO)
        val factory = HomeFragmentViewModelFactory(pokemonRepository)
        homeFragmentViewModel = ViewModelProvider(this, factory).get(HomeFragmentViewModel::class.java)


        initRecycleView()

        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        postponeEnterTransition()
        val parentView = view.parent as ViewGroup
        parentView.viewTreeObserver
            .addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    parentView.viewTreeObserver.removeOnPreDrawListener(this)
                    startPostponedEnterTransition()
                    return true
                }
            })
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecycleView() {
        binding.pokemonRecView.layoutManager = LinearLayoutManager(requireContext())
        displayPokemons()
    }

    private fun displayPokemons() {
        homeFragmentViewModel.pokemons.observe(viewLifecycleOwner, Observer { pokemons ->
            binding.pokemonRecView.adapter =
                PokemonRecycleViewAdapter(pokemons) { selectedItem: Pokemon, selectedItemImageView: ImageView ->
                    pokemonListItemCliked(
                        selectedItem,
                        selectedItemImageView
                    )
                }
        })
    }

    private fun pokemonListItemCliked(pokemon: Pokemon, selectedItemImageView: ImageView) {
        val extras = FragmentNavigatorExtras(selectedItemImageView to "image_transition")
        val action = HomeFragmentDirections.actionMiHomeToPokemonDetailFragment(pokemon)

        Navigation.findNavController(binding.root).navigate(action, extras)
    }

}