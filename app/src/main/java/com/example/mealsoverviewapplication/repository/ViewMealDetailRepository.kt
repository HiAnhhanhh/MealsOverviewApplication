package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.ViewMealDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class ViewMealDetailRepository {
    companion object {
        fun getViewMealDetail(mealId:String) : Flow<ViewMealDetail> = flow {
            val response = RetrofitBuilder.api.getMealDetail(mealId)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}