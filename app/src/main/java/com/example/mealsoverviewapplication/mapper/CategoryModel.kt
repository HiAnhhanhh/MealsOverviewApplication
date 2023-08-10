package com.example.mealsoverviewapplication.mapper

import com.example.mealsoverviewapplication.Constants
import com.example.mealsoverviewapplication.models.Category

data class CategoryModel(
    val categoryId :String?= "",
    val strCategory: String?= "",
    val strCategoryThumb : String?= "",
    val strCategoryDes : String? = ""
)

fun Category.toModel(): CategoryModel {
    return CategoryModel(
        categoryId = this.idCategory ?: Constants.EMPTY_STRING,
        strCategory = this.strCategory ?: Constants.EMPTY_STRING,
        strCategoryThumb = this.strCategoryThumb ?: Constants.EMPTY_STRING,
        strCategoryDes = this.strCategoryDescription ?: Constants.EMPTY_STRING
    )
}



