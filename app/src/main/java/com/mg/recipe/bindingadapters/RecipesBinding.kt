package com.mg.recipe.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.mg.recipe.repo.local.entities.Recipe
import com.mg.recipe.repo.network.NetworkResult
import com.mg.recipe.repo.network.NetworkResult.*
import com.mg.recipe.spoonacular.data.models.FoodRecipe

class RecipesBinding {

    companion object {

        @JvmStatic
        @BindingAdapter("readApiResponse", "readDatabase", requireAll = true)
        fun errorImageViewVisibility(
            imageView: ImageView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<Recipe>?
        ) {
            if (apiResponse is Error && database.isNullOrEmpty()) {
                imageView.visibility = View.VISIBLE
            } else if (apiResponse is Loading) {
                imageView.visibility = View.INVISIBLE
            } else if (apiResponse is Success) {
                imageView.visibility = View.INVISIBLE
            }
        }

        @JvmStatic
        @BindingAdapter("readApiResponseText", "readDatabaseText", requireAll = true)
        fun errorTextViewVisibility(
            textView: TextView,
            apiResponse: NetworkResult<FoodRecipe>?,
            database: List<Recipe>?
        ) {
            if (apiResponse is Error && database.isNullOrEmpty()) {
                textView.visibility = View.VISIBLE
                textView.text = apiResponse.message.toString()
            } else if (apiResponse is Loading) {
                textView.visibility = View.INVISIBLE
            } else if (apiResponse is Success) {
                textView.visibility = View.INVISIBLE
            }
        }
    }
}