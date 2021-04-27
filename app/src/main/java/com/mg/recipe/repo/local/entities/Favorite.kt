package com.mg.recipe.repo.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mg.recipe.repo.local.FAVORITE_TABLE
import com.mg.recipe.spoonacular.data.models.Result

@Entity(tableName = FAVORITE_TABLE)
class Favorite(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val result: Result
)