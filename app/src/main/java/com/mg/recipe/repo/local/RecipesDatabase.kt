package com.mg.recipe.repo.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mg.recipe.repo.local.converters.RecipesTypeConverter
import com.mg.recipe.repo.local.daos.FavoriteDao
import com.mg.recipe.repo.local.daos.RecipeDao
import com.mg.recipe.repo.local.entities.Favorite
import com.mg.recipe.repo.local.entities.Recipe

@Database(
    entities = [Recipe::class, Favorite::class],
    version = 1
)
@TypeConverters(RecipesTypeConverter::class)
abstract class RecipesDatabase : RoomDatabase() {

    abstract fun recipesDao(): RecipeDao

    abstract fun favoriteDao(): FavoriteDao
}