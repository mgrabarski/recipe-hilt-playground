package com.mg.recipe.ui.fragments.favorite.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mg.recipe.databinding.RowFavoriteRecipeBinding
import com.mg.recipe.repo.local.entities.Favorite

class FavoriteRecipesAdapter : RecyclerView.Adapter<FavoriteRecipesAdapter.ViewHolder>() {

    private var favoriteRecipes = emptyList<Favorite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteRecipes[position])
    }

    override fun getItemCount() = favoriteRecipes.size

    fun setData(newFavoriteRecipes: List<Favorite>) {
        val favoriteRecipesDiffUtil = RecipesDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(private val binding: RowFavoriteRecipeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritesEntity: Favorite) {
            binding.favoritesEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowFavoriteRecipeBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}