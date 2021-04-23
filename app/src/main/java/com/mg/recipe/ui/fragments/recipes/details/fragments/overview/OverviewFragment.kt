package com.mg.recipe.ui.fragments.recipes.details.fragments.overview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mg.recipe.databinding.FragmentRecipeOverviewBinding

class OverviewFragment : Fragment() {

    private var _binding: FragmentRecipeOverviewBinding? = null
    private val binding: FragmentRecipeOverviewBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRecipeOverviewBinding.inflate(inflater, container, false).root

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}