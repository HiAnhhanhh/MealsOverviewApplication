package com.example.mealsoverviewapplication.data.mapper

import com.example.mealsoverviewapplication.data.models.MealDetail

data class MealDetailModel(
    val idMeal: String? ="",
    val strMeal : String? ="",
    val strThumb : String? =""
)

fun MealDetail.toModel(): MealDetailModel {
    return MealDetailModel(
        idMeal  = this.idMeal,
        strMeal = this.strMeal  ,
        strThumb = this.strMealThumb
    )
}