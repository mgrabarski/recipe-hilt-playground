package com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mg.recipe.databinding.FragmentRecipeIngredientsBinding
import com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients.adapters.IngredientsAdapter

class IngredientsFragment : Fragment() {

    private val adapter = IngredientsAdapter()

    private var _binding: FragmentRecipeIngredientsBinding? = null
    private val binding: FragmentRecipeIngredientsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeIngredientsBinding.inflate(inflater, container, false)

        binding.ingredientsRecyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}