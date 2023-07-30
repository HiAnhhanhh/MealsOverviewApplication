package com.example.mealsoverviewapplication.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Categories(
    @SerialName("categories")
    val categories: List<Category>?
)