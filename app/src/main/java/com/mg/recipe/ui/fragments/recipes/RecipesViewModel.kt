package com.mg.recipe.ui.fragments.recipes

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import com.mg.recipe.repo.network.*
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_DIET_TYPE
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_MEAL_TYPE
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_RECIPES_NUMBER

class RecipesViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    fun getRequestQueries() = mapOf(
        QUERY_NUMBER to DEFAULT_RECIPES_NUMBER,
        QUERY_TYPE to DEFAULT_MEAL_TYPE,
        QUERY_DIET to DEFAULT_DIET_TYPE,
        QUERY_ADD_RECIPE_INFORMATION to "true",
        QUERY_FILL_INGREDIENTS to "true"
    )
}