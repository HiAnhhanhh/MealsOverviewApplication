package com.example.mealsoverviewapplication.models


import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Category(
    @SerialName("idCategory")
    val idCategory: String = "",
    @SerialName("strCategory")
    val strCategory: String ="",
    @SerialName("strCategoryDescription")
    val strCategoryDescription: String ="",
    @SerialName("strCategoryThumb")
    val strCategoryThumb: String =""
) : Parcelable