package com.mg.recipe.spoonacular.data.models


import com.google.gson.annotations.SerializedName

data class NutrientX(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("percentOfDailyNeeds")
    val percentOfDailyNeeds: Double,
    @SerializedName("title")
    val title: String,
    @SerializedName("unit")
    val unit: String
)