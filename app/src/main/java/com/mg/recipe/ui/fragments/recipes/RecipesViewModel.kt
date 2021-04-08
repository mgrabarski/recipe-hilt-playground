package com.mg.recipe.ui.fragments.recipes

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.mg.recipe.repo.network.*

class RecipesViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    fun getRequestQueries() = mapOf(
        QUERY_NUMBER to "50",
        QUERY_TYPE to "snack",
        QUERY_DIET to "vegan",
        QUERY_ADD_RECIPE_INFORMATION to "true",
        QUERY_FILL_INGREDIENTS to "true"
    )
}