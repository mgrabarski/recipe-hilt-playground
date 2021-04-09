package com.mg.recipe.ui.fragments

import android.app.Application
import android.net.ConnectivityManager
import android.net.NetworkCapabilities.*
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.mg.recipe.App
import com.mg.recipe.repo.FoodRecipesRepository
import com.mg.recipe.repo.local.entities.Recipe
import com.mg.recipe.repo.network.NetworkResult
import com.mg.recipe.repo.network.NetworkResult.*
import com.mg.recipe.spoonacular.data.models.FoodRecipe
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import android.content.Context.CONNECTIVITY_SERVICE as CONNECTIVITY_SERVICE1

// TODO: 07/04/2021 write tests

class MainViewModel @ViewModelInject constructor(
    private val repository: FoodRecipesRepository,
    application: Application
) : AndroidViewModel(application) {

    val readRecipes: LiveData<List<Recipe>> = repository.local.readDatabase().asLiveData()

    var recipesResponse: MutableLiveData<NetworkResult<FoodRecipe>> = MutableLiveData()

    fun getRecipes(queries: Map<String, String>) = viewModelScope.launch {
        getRecipesSafeCall(queries)
    }

    private suspend fun getRecipesSafeCall(queries: Map<String, String>) {
        recipesResponse.value = Loading()
        if (hasInternetConnection()) {
            try {
                val response = repository.remote.getRecipes(queries)
                recipesResponse.value = handleFoodRecipesResponse(response)

                val foodRecipe = recipesResponse.value!!.data
                if (foodRecipe != null) {
                    offlineCacheRecipes(foodRecipe)
                }

            } catch (e: Exception) {
                recipesResponse.value = Error("Recipes not found.")
            }
        } else {
            recipesResponse.value = Error("No internet connection")
        }
    }

    private fun offlineCacheRecipes(foodRecipe: FoodRecipe) {
        val recipes = Recipe(foodRecipe)
        insertRecipes(recipes)
    }

    private fun insertRecipes(recipe: Recipe) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.local.insertRecipes(recipe)
        }
    }

    private fun handleFoodRecipesResponse(response: Response<FoodRecipe>): NetworkResult<FoodRecipe> =
        when {
            response.message().toString().contains("timeout") -> Error("Timeout")
            response.code() == 402 -> Error("API Key Limited.")
            response.body()!!.results.isNullOrEmpty() -> Error("Recipes not found")
            response.isSuccessful -> Success(response.body())
            else -> Error(response.message())
        }

    private fun hasInternetConnection(): Boolean {
        val connectivityManager = getApplication<App>()
            .getSystemService(CONNECTIVITY_SERVICE1) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val capabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            capabilities.hasTransport(TRANSPORT_WIFI) -> true
            capabilities.hasTransport(TRANSPORT_CELLULAR) -> true
            capabilities.hasTransport(TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }
}