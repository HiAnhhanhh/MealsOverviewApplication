package com.example.mealsoverviewapplication.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RandomMeals(
    @SerialName("meals")
    val meals: List<Meal?>?
)