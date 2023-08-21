package com.example.mealsoverviewapplication.data.repository

import android.text.Editable
import com.example.mealsoverviewapplication.data.models.*
import kotlinx.coroutines.flow.Flow

interface RepositoriesInterface {
    fun getCategories() : Flow<Categories>
    fun getFilterMeals(letter: Editable?) : Flow<ListFilterMeals>
    fun getListMeals (category: String) : Flow<ListMealByCategory>
    fun getRandomMeal () : Flow<RandomMeal>
    fun getViewMealDetail(mealId:String) : Flow<ViewMealDetail>
}