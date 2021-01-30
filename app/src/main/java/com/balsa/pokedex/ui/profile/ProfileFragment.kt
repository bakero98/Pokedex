package com.balsa.pokedex.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.balsa.pokedex.PokedexApplication
import com.balsa.pokedex.R
import com.balsa.pokedex.databinding.FragmentProfileBinding
import com.balsa.pokedex.model.PokemonType
import javax.inject.Inject


class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var profileFragmentViewModel: ProfileFragmentViewModel
    private lateinit var viewPagerAdapter : TypesViewPagerAdapter

    @Inject
    lateinit var pokemonTypeList: List<PokemonType>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDagger()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentProfileBinding.inflate(layoutInflater)
        profileFragmentViewModel = ViewModelProvider(requireActivity()).get(ProfileFragmentViewModel::class.java)
        return binding.root
    }

    private fun injectDagger() {
        PokedexApplication.pokemonComponent.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadViewPagerTypes()
    }

    private fun loadViewPagerTypes() {
        viewPagerAdapter = TypesViewPagerAdapter(pokemonTypeList)
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.setPadding(100, 0, 100, 0)
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.beginFakeDrag()
        binding.viewPager.fakeDragBy(100f)
        binding.viewPager.endFakeDrag()
    }
}