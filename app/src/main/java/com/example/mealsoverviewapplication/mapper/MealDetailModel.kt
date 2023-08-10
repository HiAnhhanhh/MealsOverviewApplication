package com.example.mealsoverviewapplication.mapper

import com.example.mealsoverviewapplication.Constants
import com.example.mealsoverviewapplication.models.MealDetail

data class MealDetailModel(
    val idMeal: String? ="",
    val strMeal : String? ="",
    val strThumb : String? =""
)

fun MealDetail.toModel(): MealDetailModel {
    return MealDetailModel(
        idMeal  = this.idMeal ?: Constants.EMPTY_STRING,
        strMeal = this.strMeal ?: Constants.EMPTY_STRING ,
        strThumb = this.strMealThumb ?: Constants.EMPTY_STRING
    )
}