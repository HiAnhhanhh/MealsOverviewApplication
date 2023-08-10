package com.example.mealsoverviewapplication.mapper

import com.example.mealsoverviewapplication.Constants
import com.example.mealsoverviewapplication.models.MealDetail

data class IngredientModel(
    val idMeal: String? ="",
    val strMeal : String? ="",
    val strThumb : String? ="",
    val strIns : String? ="",
    val ingredient1: String? ="",
    val ingredient2: String? ="",
    val ingredient3: String? ="",
    val ingredient4: String? ="",
    val ingredient5: String? ="",
    val ingredient6: String? ="",
    val ingredient7: String? ="",
    val ingredient8: String? ="",
    val ingredient9: String? ="",
    val ingredient10: String? ="",
    val ingredient11: String? ="",
    val ingredient12: String? ="",
    val ingredient13: String? ="",
    val ingredient14: String? ="",
    val ingredient15: String? ="",
    val ingredient16: String? ="",
    val ingredient17: String? ="",
    val ingredient18: String? ="",
    val ingredient19: String? ="",
    val ingredient20: String? ="",

    val measure1 : String? ="",
    val measure2 : String? ="",
    val measure3 : String? ="",
    val measure4 : String? ="",
    val measure5 : String? ="",
    val measure6 : String? ="",
    val measure7 : String? ="",
    val measure8 : String? ="",
    val measure9 : String? ="",
    val measure10 : String? ="",
    val measure11 : String? ="",
    val measure12 : String? ="",
    val measure13 : String? ="",
    val measure14 : String? ="",
    val measure15 : String? ="",
    val measure16 : String? ="",
    val measure17 : String? ="",
    val measure18 : String? ="",
    val measure19 : String? ="",
    val measure20 : String? ="",
    )

fun MealDetail.toModelIngredient(): IngredientModel {
    return IngredientModel (
        idMeal = this.idMeal?: Constants.EMPTY_STRING,
        strMeal = this.strMeal?: Constants.EMPTY_STRING,
        strThumb = this.strMealThumb?:Constants.EMPTY_STRING,
        strIns = this.strInstructions?:Constants.EMPTY_STRING,
        ingredient1 = this.strIngredient1?: Constants.EMPTY_STRING,
        ingredient2 = this.strIngredient2?: Constants.EMPTY_STRING,
        ingredient3 = this.strIngredient3?: Constants.EMPTY_STRING,
        ingredient4 = this.strIngredient4?: Constants.EMPTY_STRING,
        ingredient5 = this.strIngredient5?: Constants.EMPTY_STRING,
        ingredient6 = this.strIngredient6?: Constants.EMPTY_STRING,
        ingredient7 = this.strIngredient7?: Constants.EMPTY_STRING,
        ingredient8 = this.strIngredient8?: Constants.EMPTY_STRING,
        ingredient9 = this.strIngredient9?: Constants.EMPTY_STRING,
        ingredient10 = this.strIngredient10?: Constants.EMPTY_STRING,
        ingredient11 = this.strIngredient11?: Constants.EMPTY_STRING,
        ingredient12 = this.strIngredient12?: Constants.EMPTY_STRING,
        ingredient13 = this.strIngredient13?: Constants.EMPTY_STRING,
        ingredient14 = this.strIngredient14?: Constants.EMPTY_STRING,
        ingredient15 = this.strIngredient15?: Constants.EMPTY_STRING,
        ingredient16 = this.strIngredient16?: Constants.EMPTY_STRING,
        ingredient17 = this.strIngredient17?: Constants.EMPTY_STRING,
        ingredient18 = this.strIngredient18?: Constants.EMPTY_STRING,
        ingredient19 = this.strIngredient19?: Constants.EMPTY_STRING,
        ingredient20 = this.strIngredient20?: Constants.EMPTY_STRING,

        measure1 = this.strMeasure1?: Constants.EMPTY_STRING,
        measure2 = this.strMeasure2?: Constants.EMPTY_STRING,
        measure3 = this.strMeasure3?: Constants.EMPTY_STRING,
        measure4 = this.strMeasure4?: Constants.EMPTY_STRING,
        measure5 = this.strMeasure5?: Constants.EMPTY_STRING,
        measure6 = this.strMeasure6?: Constants.EMPTY_STRING,
        measure7 = this.strMeasure7?: Constants.EMPTY_STRING,
        measure8 = this.strMeasure8?: Constants.EMPTY_STRING,
        measure9 = this.strMeasure9?: Constants.EMPTY_STRING,
        measure10 = this.strMeasure10?: Constants.EMPTY_STRING,
        measure11 = this.strMeasure11?: Constants.EMPTY_STRING,
        measure12 = this.strMeasure12?: Constants.EMPTY_STRING,
        measure13 = this.strMeasure13?: Constants.EMPTY_STRING,
        measure14 = this.strMeasure14?: Constants.EMPTY_STRING,
        measure15 = this.strMeasure15?: Constants.EMPTY_STRING,
        measure16 = this.strMeasure16?: Constants.EMPTY_STRING,
        measure17 = this.strMeasure17?: Constants.EMPTY_STRING,
        measure18 = this.strMeasure18?: Constants.EMPTY_STRING,
        measure19 = this.strMeasure19?: Constants.EMPTY_STRING,
        measure20 = this.strMeasure20?: Constants.EMPTY_STRING
    )
}
