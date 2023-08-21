package com.example.mealsoverviewapplication.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListMealByCategory(
    @SerialName("meals")
    val meals: List<MealDetail>?
)