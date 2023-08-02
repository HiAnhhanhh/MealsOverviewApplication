package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.RandomMeals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.Dispatcher

class MealIngredientsRepository {
    companion object {
        fun getRandomMeals() : Flow<RandomMeals> = flow {
            val response = RetrofitBuilder.api_1.getRandomMeals()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}