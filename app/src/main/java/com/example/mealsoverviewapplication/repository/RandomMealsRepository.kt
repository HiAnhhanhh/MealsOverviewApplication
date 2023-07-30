package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.RandomMeals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RandomMealsRepository {
    companion object {
        fun getRandomMeals(): Flow<RandomMeals> = flow {
            val response =  RetrofitBuilder.api.getRandomMeals()
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}