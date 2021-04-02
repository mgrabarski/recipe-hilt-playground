package com.mg.recipe.spoonacular.data.models


import com.google.gson.annotations.SerializedName

data class WinePairing(
    @SerializedName("pairedWines")
    val pairedWines: List<Any>,
    @SerializedName("pairingText")
    val pairingText: String,
    @SerializedName("productMatches")
    val productMatches: List<Any>
)