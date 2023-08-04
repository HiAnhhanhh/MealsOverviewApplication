package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.RandomMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MealIngredientsRepository {
    companion object {
        fun getRandomMeals() : Flow<RandomMeal> = flow {
            val response = RetrofitBuilder.api.getRandomMeals()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}