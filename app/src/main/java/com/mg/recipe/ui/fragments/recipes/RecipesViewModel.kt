package com.mg.recipe.ui.fragments.recipes

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel

class RecipesViewModel @ViewModelInject constructor(
    application: Application
) : AndroidViewModel(application) {

    fun getRequestQueries() = mapOf(
        "number" to "50",
        "type" to "snack",
        "diet" to "vegan"
    )
}