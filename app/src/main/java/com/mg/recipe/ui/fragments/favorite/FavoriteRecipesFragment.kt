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

    private val mAdapter: FavoriteRecipesAdapter by lazy { FavoriteRecipesAdapter(requireActivity(), mMainViewModel) }
    private val mMainViewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentFavoriteReceipesBinding.inflate(inflater, container, false).apply {
        lifecycleOwner = this@FavoriteRecipesFragment
        mainViewModel = mMainViewModel
        favoriteRecipesRecyclerView.adapter = mAdapter
        adapter = mAdapter
    }.root
}