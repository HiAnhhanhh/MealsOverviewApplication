package com.example.mealsoverviewapplication.models


import com.example.mealsoverviewapplication.mapper.MealDetailModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ListMealByCategory(
    @SerialName("meals")
    val meals: List<MealDetail>?
)