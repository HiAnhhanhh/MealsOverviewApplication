package com.example.mealsoverviewapplication.data.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListFilterMeals(
    @SerialName("meals")
    val meals: List<MealDetail>?
)