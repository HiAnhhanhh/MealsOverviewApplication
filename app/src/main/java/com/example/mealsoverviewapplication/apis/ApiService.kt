package com.example.mealsoverviewapplication.apis


import com.example.mealsoverviewapplication.models.Categories
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.RandomMeals
import retrofit2.http.GET


interface ApiService {
//    @GET("categories.php")
//    fun getCategory() : List<Category>

    @GET("categories.php")
    suspend fun getCategories (): Categories

    @GET("random.php")
    suspend fun getRandomMeals (): RandomMeals
}