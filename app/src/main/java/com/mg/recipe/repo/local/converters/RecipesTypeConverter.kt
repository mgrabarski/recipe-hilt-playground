package com.mg.recipe.repo.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mg.recipe.spoonacular.data.models.FoodRecipe

class RecipesTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String = gson.toJson(foodRecipe)

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe = gson.fromJson(data, FoodRecipe::class.java)
}