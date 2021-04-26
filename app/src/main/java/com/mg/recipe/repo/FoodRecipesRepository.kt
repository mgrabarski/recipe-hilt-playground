package com.mg.recipe.repo

import com.mg.recipe.repo.sources.RemoteDataSource
import com.mg.recipe.repo.sources.LocalDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class FoodRecipesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) {

    val remote
        get() = remoteDataSource

    val local
        get() = localDataSource
}