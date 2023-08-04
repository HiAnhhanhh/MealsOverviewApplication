package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.RandomMeal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class RandomMealRepository {

    companion object {
        fun getRandomMeal () : Flow<RandomMeal> = flow {
            val response = RetrofitBuilder.api.getRandomMeal()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }

}