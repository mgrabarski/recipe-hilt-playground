package com.mg.recipe.repo.local.daos

import androidx.room.*
import com.mg.recipe.repo.local.FAVORITE_TABLE
import com.mg.recipe.repo.local.entities.Favorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(favorite: Favorite)

    @Query("SELECT * FROM $FAVORITE_TABLE")
    fun readAllFavorites(): Flow<List<Favorite>>

    @Delete
    suspend fun delete(favorite: Favorite)

    @Query("DELETE FROM $FAVORITE_TABLE")
    suspend fun deleteAll()
}