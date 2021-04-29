package com.mg.recipe.ui.fragments.favorite.adapter

import android.view.*
import androidx.core.content.ContextCompat
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

    private lateinit var actionMode: ActionMode

    private var viewHolders = arrayListOf<ViewHolder>()
    private var multiSelection = false
    private val selectedRecipes = arrayListOf<Favorite>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        viewHolders.add(holder)
        val currentFavorite = favoriteRecipes[position]
        holder.bind(currentFavorite)

        holder.binding.recipesRowLayout.setOnClickListener {
            if (multiSelection) {
                applySelection(holder, currentFavorite)
            } else {
                it.findNavController().navigate(
                    actionFavoriteReceipesFragmentToRecipeDetailsActivity(currentFavorite.result)
                )
            }
        }
        holder.binding.recipesRowLayout.setOnLongClickListener {
            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentFavorite)
                true
            } else {
                multiSelection = false
                false
            }
        }
    }

    override fun getItemCount() = favoriteRecipes.size

    private fun applySelection(holder: ViewHolder, favorite: Favorite) {
        if (selectedRecipes.contains(favorite)) {
            selectedRecipes.remove(favorite)
            changeFavoriteStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(favorite)
            changeFavoriteStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
            applyActionModeTitle()
        }
    }

    private fun changeFavoriteStyle(holder: ViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.binding.recipesRowLayout.setBackgroundColor(
            ContextCompat.getColor(requireActivity, backgroundColor)
        )
        holder.binding.favoriteRowCardView.strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionModeTitle() {
        when (selectedRecipes.size) {
            0 -> actionMode.finish()
        }
    }

    fun setData(newFavoriteRecipes: List<Favorite>) {
        val favoriteRecipesDiffUtil = AppDiffUtil(favoriteRecipes, newFavoriteRecipes)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipes
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorites_delete_menu, menu)
        actionMode = mode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean = true

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean = true

    override fun onDestroyActionMode(mode: ActionMode?) {
        viewHolders.forEach {
            changeFavoriteStyle(it, R.color.cardBackgroundColor, R.color.strokeColor)
        }
        multiSelection = false
        selectedRecipes.clear()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor = ContextCompat.getColor(requireActivity, color)
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