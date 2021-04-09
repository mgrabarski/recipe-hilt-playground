package com.mg.recipe.di

import android.content.Context
import androidx.room.Room
import com.mg.recipe.repo.local.DATABASE_NAME
import com.mg.recipe.repo.local.RecipesDatabase
import com.mg.recipe.repo.local.daos.RecipeDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context,
        RecipesDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Singleton
    @Provides
    fun provideRecipesDao(database: RecipesDatabase): RecipeDao = database.recipesDao()
}
