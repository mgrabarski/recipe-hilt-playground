package com.mg.recipe.repo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mg.recipe.repo.local.RECIPES_TABLE
import com.mg.recipe.spoonacular.data.models.FoodRecipe

@Entity(tableName = RECIPES_TABLE)
class Recipe(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0
}