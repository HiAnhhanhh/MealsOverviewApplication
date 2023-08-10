package com.example.mealsoverviewapplication.mapper

import com.example.mealsoverviewapplication.models.MealDetail

data class MealDetailModel(
    val id: String? ="",
    val strMeal : String? ="",
    val strThumb : String? =""
)

fun MealDetail.toModel(): MealDetailModel {
    return MealDetailModel(
        id  = this.idMeal,
        strMeal = this.strMeal,
        strThumb = this.strMealThumb
    )
}