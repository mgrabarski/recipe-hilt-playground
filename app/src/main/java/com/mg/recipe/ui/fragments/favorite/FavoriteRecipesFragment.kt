package com.mg.recipe.ui.fragments.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mg.recipe.databinding.FragmentFavoriteReceipesBinding
import com.mg.recipe.ui.fragments.MainViewModel
import com.mg.recipe.ui.fragments.favorite.adapter.FavoriteRecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteRecipesFragment : Fragment() {

    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter() }
    private val mainViewModel: MainViewModel by viewModels()

    private var _binding: FragmentFavoriteReceipesBinding? = null
    private val binding: FragmentFavoriteReceipesBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteReceipesBinding.inflate(inflater, container, false)
        binding.apply {
            lifecycleOwner = this@FavoriteRecipesFragment
            mainViewModel = this@FavoriteRecipesFragment.mainViewModel
            mAdapter = this@FavoriteRecipesFragment.mAdapter
        }
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}