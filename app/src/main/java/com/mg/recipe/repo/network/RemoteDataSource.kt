package com.mg.recipe.repo.network

import com.mg.recipe.BuildConfig
import com.mg.recipe.spoonacular.FoodRecipesApi
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi
) {

    suspend fun getRecipes(queries: Map<String, String>) =
        foodRecipesApi.getRecipes(queries.toMutableMap().apply {
            put(QUERY_API_KEY, BuildConfig.SPOONACULAR_API_KEY)
        })
}