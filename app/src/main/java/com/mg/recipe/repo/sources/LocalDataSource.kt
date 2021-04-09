package com.mg.recipe.repo.sources

import com.mg.recipe.repo.local.daos.RecipeDao
import com.mg.recipe.repo.local.entities.Recipe
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao
) {

    fun readDatabase() = recipeDao.getAll()

    suspend fun insertRecipes(recipe: Recipe) {
        recipeDao.insert(recipe)
    }
}