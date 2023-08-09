package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.ListMealByCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ListMealsRepository {
    companion object {
        fun getListMeals (category: String) : Flow<ListMealByCategory> = flow {
            val response = RetrofitBuilder.api.getListMeals(category)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}