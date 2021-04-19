package com.mg.recipe.ui.fragments.recipes

import android.app.Application
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.mg.recipe.repo.DataStoreRepository
import com.mg.recipe.repo.network.*
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_DIET_TYPE
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_MEAL_TYPE
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_RECIPES_NUMBER
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class RecipesViewModel @ViewModelInject constructor(
    application: Application,
    private val foodDataStoreRepository: DataStoreRepository
) : AndroidViewModel(application) {

    private var mealType = DEFAULT_MEAL_TYPE
    private var dietType = DEFAULT_DIET_TYPE

    var networkState = false

    val readMealAndDietType = foodDataStoreRepository.readMealAndDietType

    fun saveMealAndDietType(
        selectedMealType: String,
        selectedMealTypeId: Int,
        selectedDietType: String,
        selectedDietTypeId: Int
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            foodDataStoreRepository.saveMealAndDietType(
                selectedMealType,
                selectedMealTypeId,
                selectedDietType,
                selectedDietTypeId
            )
        }
    }

    fun getRequestQueries(): Map<String, String> {

        viewModelScope.launch {
            readMealAndDietType.collect {
                mealType = it.selectedMealType
                dietType = it.selectedDietType
            }
        }

        return mapOf(
            QUERY_NUMBER to DEFAULT_RECIPES_NUMBER,
            QUERY_TYPE to mealType,
            QUERY_DIET to dietType,
            QUERY_ADD_RECIPE_INFORMATION to "true",
            QUERY_FILL_INGREDIENTS to "true"
        )
    }

    fun showNetworkState() {
        if (!networkState) {
            Toast.makeText(getApplication(), "No internet connection", Toast.LENGTH_SHORT).show()
        }
    }
}