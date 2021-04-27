package com.mg.recipe.bindingadapters

import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.mg.recipe.repo.local.entities.Favorite
import com.mg.recipe.ui.fragments.favorite.FavoriteRecipesFragmentDirections.Companion.actionFavoriteReceipesFragmentToRecipeDetailsActivity
import com.mg.recipe.ui.fragments.favorite.adapter.FavoriteRecipesAdapter

@BindingAdapter("viewVisibility", "setData", requireAll = false)
fun setDataAndViewVisibility(
    view: View,
    favoritesEntity: List<Favorite>?,
    adapter: FavoriteRecipesAdapter?
) {
    if (favoritesEntity.isNullOrEmpty()) {
        when (view) {
            is ImageView -> view.visibility = VISIBLE
            is TextView -> view.visibility = VISIBLE
            is RecyclerView -> view.visibility = INVISIBLE
        }
    } else {
        when (view) {
            is ImageView -> view.visibility = INVISIBLE
            is TextView -> view.visibility = INVISIBLE
            is RecyclerView -> {
                view.visibility = VISIBLE
                adapter?.setData(favoritesEntity)
            }
        }
    }
}

@BindingAdapter("onFavoriteClickListener")
fun onFavoriteClickListener(rowLayout: ConstraintLayout, favorite: Favorite) {
    rowLayout.setOnClickListener {
        rowLayout.findNavController().navigate(
            actionFavoriteReceipesFragmentToRecipeDetailsActivity(favorite.result)
        )
    }
}