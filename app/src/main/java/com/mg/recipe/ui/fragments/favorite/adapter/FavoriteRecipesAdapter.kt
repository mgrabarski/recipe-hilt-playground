package com.mg.recipe.ui.fragments.favorite.adapter

import android.view.*
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mg.recipe.R
import com.mg.recipe.components.AppDiffUtil
import com.mg.recipe.databinding.RowFavoriteRecipeBinding
import com.mg.recipe.repo.local.entities.Favorite
import com.mg.recipe.ui.fragments.favorite.FavoriteRecipesFragmentDirections.Companion.actionFavoriteReceipesFragmentToRecipeDetailsActivity

class FavoriteRecipesAdapter(
    private val requireActivity: FragmentActivity
) : RecyclerView.Adapter<FavoriteRecipesAdapter.ViewHolder>(), ActionMode.Callback {

    private var favoriteRecipes = emptyList<Favorite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val selectedFavorite = favoriteRecipes[position]
        holder.bind(selectedFavorite)

        holder.binding.recipesRowLayout.setOnClickListener {
            it.findNavController().navigate(
                actionFavoriteReceipesFragmentToRecipeDetailsActivity(selectedFavorite.result)
            )
        }
        holder.binding.recipesRowLayout.setOnLongClickListener {
            requireActivity.startActionMode(this)
            true
        }
    }

    override fun getItemCount() = favoriteRecipes.size

    fun setData(newFavoriteRecipes: List<Favorite>) {
        val favoriteRecipesDiffUtil = AppDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorites_delete_menu, menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = true

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = true

    override fun onDestroyActionMode(mode: ActionMode?) {
    }

    class ViewHolder(val binding: RowFavoriteRecipeBinding) :
        RecyclerView.ViewHolder(binding.root) {

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