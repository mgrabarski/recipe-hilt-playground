package com.mg.recipe.ui.fragments.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.mg.recipe.BuildConfig
import com.mg.recipe.databinding.FragmentRecipesBinding
import com.mg.recipe.repo.network.NetworkResult.Loading
import com.mg.recipe.repo.network.NetworkResult.Success
import com.mg.recipe.ui.fragments.MainViewModel
import com.mg.recipe.ui.fragments.recipes.adapters.RecipesAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val mainViewModel: MainViewModel by viewModels()
    private val recipesViewModel: RecipesViewModel by viewModels()
    private val mAdapter by lazy { RecipesAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentRecipesBinding.inflate(inflater, container, false)
        .apply {
            this.lifecycleOwner = this@RecipesFragment
            this.recipesRv.adapter = mAdapter
            mainViewModel.getRecipes(recipesViewModel.getRequestQueries())
            mainViewModel.recipesResponse.observe(viewLifecycleOwner, { response ->
                when (response) {
                    is Loading -> {
                    }
                    is Success -> response.data?.let { mAdapter.setData(it) }
                    is Error -> Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_LONG
                    ).show()
                    else -> {
                    }
                }
            })
        }
        .root
}