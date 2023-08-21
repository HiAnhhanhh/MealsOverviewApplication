package com.example.mealsoverviewapplication.data.mapper

import com.example.mealsoverviewapplication.data.models.Category

data class CategoryModel(
    val categoryId :String?= "",
    val strCategory: String?= "",
    val strCategoryThumb : String?= "",
    val strCategoryDes : String? = ""
)

fun Category.toModel(): CategoryModel {
    return CategoryModel(
        categoryId = this.idCategory ,
        strCategory = this.strCategory,
        strCategoryThumb = this.strCategoryThumb,
        strCategoryDes = this.strCategoryDescription
    )
}



