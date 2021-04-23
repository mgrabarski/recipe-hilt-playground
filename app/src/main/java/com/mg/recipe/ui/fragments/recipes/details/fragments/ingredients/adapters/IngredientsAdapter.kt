package com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mg.recipe.databinding.RowIngredientBinding
import com.mg.recipe.spoonacular.data.models.ExtendedIngredient
import com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients.adapters.IngredientsAdapter.IngredientViewHolder
import com.mg.recipe.ui.fragments.recipes.details.fragments.ingredients.adapters.IngredientsAdapter.IngredientViewHolder.Companion.from

class IngredientsAdapter : RecyclerView.Adapter<IngredientViewHolder>() {

    private val ingredients = mutableListOf<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = from(parent)

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.bind(ingredients[position])
    }

    override fun getItemCount() = ingredients.size

    class IngredientViewHolder(val binding: RowIngredientBinding) : ViewHolder(binding.root) {

        fun bind(extendedIngredient: ExtendedIngredient) {
            binding.ingredient = extendedIngredient
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): IngredientViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RowIngredientBinding.inflate(inflater, parent, false)
                return IngredientViewHolder(binding)
            }
        }
    }
}