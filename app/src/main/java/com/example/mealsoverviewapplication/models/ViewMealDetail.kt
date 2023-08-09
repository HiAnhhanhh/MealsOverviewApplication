package com.example.mealsoverviewapplication.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ViewMealDetail(
    @SerialName("meals")
    val meals: List<MealDetail>?
)