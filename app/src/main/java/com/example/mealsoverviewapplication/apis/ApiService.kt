package com.example.mealsoverviewapplication.apis


import android.text.Editable
import com.example.mealsoverviewapplication.models.*
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("categories.php")
    suspend fun getCategories (): Categories

    @GET("random.php")
    suspend fun getRandomMeals (): RandomMeal

    @GET("search.php")
    suspend fun getFilterMeals (@Query("f") letter: Editable?) : ListFilterMeals

    @GET("random.php")
    suspend fun getRandomMeal() : RandomMeal

    @GET("filter.php")
    suspend fun getListMeals(@Query("c") category: String) : ListMealByCategory

    @GET("lookup.php")
    suspend fun getMealDetail (@Query("i") mealId : String) : ViewMealDetail

}