package com.mg.recipe.ui.fragments.recipes.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mg.recipe.databinding.RowReceipeBinding
import com.mg.recipe.spoonacular.data.models.FoodRecipe
import com.mg.recipe.spoonacular.data.models.Result
import com.mg.recipe.ui.fragments.recipes.adapters.RecipesAdapter.RecipeViewHolder
import com.mg.recipe.ui.fragments.recipes.adapters.RecipesAdapter.RecipeViewHolder.Companion.from

class RecipesAdapter : RecyclerView.Adapter<RecipeViewHolder>() {

    private var recipes = listOf<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = from(parent)

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    fun setData(newData: FoodRecipe) {
        recipes = newData.results
        notifyDataSetChanged()
    }

    class RecipeViewHolder(private val binding: RowReceipeBinding) : ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): RecipeViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val binding = RowReceipeBinding.inflate(inflater, parent, false)
                return RecipeViewHolder(binding)
            }
        }
    }
}