package com.mg.recipe.repo.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mg.recipe.repo.local.RECIPES_TABLE
import com.mg.recipe.repo.local.entities.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recipe: Recipe)

    @Query("SELECT * FROM $RECIPES_TABLE ORDER BY id ASC")
    fun getAll(): Flow<List<Recipe>>
}