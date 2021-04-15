package com.mg.recipe.repo

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.*
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_DIET_TYPE
import com.mg.recipe.ui.fragments.recipes.bottomsheet.consts.DEFAULT_MEAL_TYPE
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

private const val PREFERENCES_NAME = "foodly_preferences"

private const val PREFERENCES_MEAL_TYPE = "mealType"
private const val PREFERENCES_MEAL_TYPE_ID = "mealTypeId"
private const val PREFERENCES_DIET_TYPE = "dietType"
private const val PREFERENCES_DIET_TYPE_ID = "dietTypeId"

@ActivityRetainedScoped
class DataStoreRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private object PreferencesKeys {
        val selectedMealType = preferencesKey<String>(PREFERENCES_MEAL_TYPE)
        val selectedMealTypeId = preferencesKey<Int>(PREFERENCES_MEAL_TYPE_ID)
        val selectedDietType = preferencesKey<String>(PREFERENCES_DIET_TYPE)
        val selectedDietTypeId = preferencesKey<Int>(PREFERENCES_DIET_TYPE_ID)
    }

    private val dataStore: DataStore<Preferences> = context.createDataStore(
        name = PREFERENCES_NAME
    )

    suspend fun saveMealAndDietType(
        mealType: String,
        mealTypeId: Int,
        dietType: String,
        dietTypeId: Int
    ) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.selectedMealType] = mealType
            preferences[PreferencesKeys.selectedMealTypeId] = mealTypeId
            preferences[PreferencesKeys.selectedDietType] = dietType
            preferences[PreferencesKeys.selectedDietTypeId] = dietTypeId
        }
    }

    val readMealAndDietType: Flow<MealAndDietType> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            MealAndDietType(
                selectedMealType = preferences[PreferencesKeys.selectedMealType]
                    ?: DEFAULT_MEAL_TYPE,
                selectedMealTypeId = preferences[PreferencesKeys.selectedMealTypeId] ?: 0,
                selectedDietType = preferences[PreferencesKeys.selectedDietType]
                    ?: DEFAULT_DIET_TYPE,
                selectedDietTypeId = preferences[PreferencesKeys.selectedDietTypeId] ?: 0
            )
        }
}

data class MealAndDietType(
    val selectedMealType: String,
    val selectedMealTypeId: Int,
    val selectedDietType: String,
    val selectedDietTypeId: Int
)