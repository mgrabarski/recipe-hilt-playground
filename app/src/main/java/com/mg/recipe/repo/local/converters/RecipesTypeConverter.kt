package com.mg.recipe.repo.local.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mg.recipe.spoonacular.data.models.FoodRecipe
import com.mg.recipe.spoonacular.data.models.Result

class RecipesTypeConverter {

    private val gson = Gson()

    @TypeConverter
    fun foodRecipeToString(foodRecipe: FoodRecipe): String = gson.toJson(foodRecipe)

    @TypeConverter
    fun stringToFoodRecipe(data: String): FoodRecipe = gson.fromJson(data, FoodRecipe::class.java)

    @TypeConverter
    fun resultToString(result: Result): String {
        return gson.toJson(result)
    }

    @TypeConverter
    fun stringToResult(data: String): Result = gson.fromJson(data, Result::class.java)
}