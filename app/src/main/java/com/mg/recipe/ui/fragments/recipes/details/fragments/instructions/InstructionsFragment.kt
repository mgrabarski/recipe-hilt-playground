package com.mg.recipe.ui.fragments.recipes.details.fragments.instructions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.mg.recipe.databinding.FragmentRecipeInstructionsBinding
import com.mg.recipe.spoonacular.data.models.Result
import com.mg.recipe.ui.KEY_RECIPE_BUNDLE

class InstructionsFragment : Fragment() {

    private var _binding: FragmentRecipeInstructionsBinding? = null
    private val binding: FragmentRecipeInstructionsBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeInstructionsBinding.inflate(inflater, container, false)

        binding.webView.webViewClient = object : WebViewClient() {}

        arguments?.getSerializable(KEY_RECIPE_BUNDLE)?.let {
            val result = it as Result
            binding.webView.loadUrl(result.sourceUrl)
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}