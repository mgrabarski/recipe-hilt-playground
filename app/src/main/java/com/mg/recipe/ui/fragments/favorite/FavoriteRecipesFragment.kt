package com.mg.recipe.ui.fragments.favorite

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mg.recipe.R
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

        setHasOptionsMenu(true)
    }.root

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.favorite_recipe_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all_favorites) {
            mMainViewModel.deleteAllFavoriteRecipes()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdapter.closeContextualMenu()
    }
}