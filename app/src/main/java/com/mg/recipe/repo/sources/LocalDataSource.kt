package com.mg.recipe.repo.sources

import com.mg.recipe.repo.local.daos.FavoriteDao
import com.mg.recipe.repo.local.daos.RecipeDao
import com.mg.recipe.repo.local.entities.Favorite
import com.mg.recipe.repo.local.entities.Recipe
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val recipeDao: RecipeDao,
    private val favoriteDao: FavoriteDao
) {

    fun readDatabase() = recipeDao.getAll()

    fun readAllFavorite() = favoriteDao.readAllFavorites()

    suspend fun insertRecipes(recipe: Recipe) {
        recipeDao.insert(recipe)
    }

    suspend fun insertFavorite(favorite: Favorite) {
        favoriteDao.insert(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorite) {
        favoriteDao.delete(favorite)
    }

    suspend fun deleteAllFavorites() {
        favoriteDao.deleteAll()
    }
}