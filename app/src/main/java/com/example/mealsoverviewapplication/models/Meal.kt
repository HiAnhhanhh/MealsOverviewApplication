package com.example.mealsoverviewapplication.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meal(
    @SerialName("idMeal")
    val idMeal: String="",
    @SerialName("strMeal")
    val strMeal: String="",
    @SerialName("strMealThumb")
    val strMealThumb: String=""
)