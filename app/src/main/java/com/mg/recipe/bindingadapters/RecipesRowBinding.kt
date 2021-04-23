package com.mg.recipe.bindingadapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.navigation.findNavController
import coil.load
import com.mg.recipe.R
import com.mg.recipe.spoonacular.data.models.Result
import com.mg.recipe.ui.fragments.recipes.RecipesFragmentDirections.Companion.actionReceipesFragmentToRecipeDetailsActivity

@BindingAdapter("loadImageFromUrl")
fun loadImageFromUrl(view: ImageView, url: String) {
    view.load(url) {
        crossfade(600)
        error(R.drawable.ic_error_red)
    }
}

@BindingAdapter("setNumberOfLikes")
fun setNumberOfLikes(view: TextView, likes: Int) {
    view.text = likes.toString()
}

@BindingAdapter("setNumberOfMinutes")
fun setNumberOfMinutes(view: TextView, minutes: Int) {
    view.text = minutes.toString()
}

@BindingAdapter("applyGreenColorOnView")
fun applyGreenColorOnView(view: View, apply: Boolean) {
    if (apply) {
        val greenColor = ContextCompat.getColor(view.context, R.color.green)
        when (view) {
            is TextView -> view.setTextColor(greenColor)
            is ImageView -> view.setColorFilter(greenColor)
        }
    }
}

@BindingAdapter("onRecipeClickListener")
fun onRecipeClickListener(recipeRowLayout: ConstraintLayout, result: Result) {
    recipeRowLayout.setOnClickListener {
        val action = actionReceipesFragmentToRecipeDetailsActivity(result)
        recipeRowLayout.findNavController().navigate(action)
    }
}