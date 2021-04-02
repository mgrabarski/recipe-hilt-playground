package com.mg.recipe.spoonacular.data.models


import com.google.gson.annotations.SerializedName

data class Flavanoid(
    @SerializedName("amount")
    val amount: Double,
    @SerializedName("name")
    val name: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("unit")
    val unit: String
)