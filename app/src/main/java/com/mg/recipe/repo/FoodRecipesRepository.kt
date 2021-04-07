package com.mg.recipe.repo

import com.mg.recipe.repo.network.RemoteDataSource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class FoodRecipesRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) {

    val remote
        get() = remoteDataSource
}