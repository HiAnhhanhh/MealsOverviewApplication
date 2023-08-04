package com.example.mealsoverviewapplication.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MealDetail(
    val strCategory: String = "",
    val strCategoryThumb: String = "",
    val description: String ="",
    val categoryId : String =""
): Parcelable