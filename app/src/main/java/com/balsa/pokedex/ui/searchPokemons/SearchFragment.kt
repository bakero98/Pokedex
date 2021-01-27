package com.balsa.pokedex.ui.searchPokemons

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar;
import androidx.annotation.Nullable
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.balsa.pokedex.PokedexApplication
import com.balsa.pokedex.R
import com.balsa.pokedex.databinding.FragmentSearchBinding
import com.balsa.pokedex.model.Pokemon
import com.balsa.pokedex.repos.PokemonRepository
import com.balsa.pokedex.ui.home.HomeFragmentDirections
import com.balsa.pokedex.ui.home.PokemonRecycleViewAdapter
import javax.inject.Inject


class SearchFragment : Fragment(), SearchView.OnQueryTextListener {

    private lateinit var binding : FragmentSearchBinding
    private lateinit var searchFragmentViewModel : SearchFragmentViewModel

    @Inject
    lateinit var searchFragmentViewModelFactory: SearchFragmentViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        searchFragmentViewModel = ViewModelProvider(requireActivity(), searchFragmentViewModelFactory).get(SearchFragmentViewModel::class.java)
//        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onViewCreated(view: View, @Nullable savedInstanceState: Bundle?) {
        createToolbar()
        initRecycleView()
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

    private fun injectDagger() {
        PokedexApplication.pokemonComponent.inject(this)
    }

    private fun initRecycleView() {
        binding.pokemonRecView.layoutManager = LinearLayoutManager(requireContext())
        displayPokemons()
    }

    private fun displayPokemons() {
        searchFragmentViewModel.pokemons.observe(viewLifecycleOwner, Observer { pokemons ->
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
        val action = SearchFragmentDirections.actionMiSearchToPokemonDetailFragment(pokemon)

        Navigation.findNavController(binding.root).navigate(action, extras)
    }

    private fun createToolbar() {
        binding.toolbar.inflateMenu(R.menu.search_menu)

        val search = binding.toolbar.menu.findItem(R.id.menuSearch)
        val searchView = search.actionView as? SearchView
        searchView?.isSubmitButtonEnabled = false
        searchView?.setOnQueryTextListener(this)
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        if (p0 != null) {
            searchFragmentViewModel.searchQuery.value = p0
        }
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        if (p0 != null) {
            searchFragmentViewModel.searchQuery.value = p0
        }
        return true
    }
}