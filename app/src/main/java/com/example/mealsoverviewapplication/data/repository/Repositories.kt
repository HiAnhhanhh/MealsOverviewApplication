package com.example.mealsoverviewapplication.data.repository

import android.text.Editable
import com.example.mealsoverviewapplication.data.api.RetrofitBuilder
import com.example.mealsoverviewapplication.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class Repositories : RepositoriesInterface {

    override fun getCategories(): Flow<Categories> = flow {
            val response =  RetrofitBuilder.api.getCategories()
            emit(response)
        }.flowOn(Dispatchers.IO)

    override fun getFilterMeals(letter: Editable?): Flow<ListFilterMeals> = flow {
        val response = RetrofitBuilder.api.getFilterMeals(letter)
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getListMeals(category: String): Flow<ListMealByCategory> = flow {
        val response = RetrofitBuilder.api.getListMeals(category)
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getRandomMeal(): Flow<RandomMeal> = flow {
        val response = RetrofitBuilder.api.getRandomMeal()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override fun getViewMealDetail(mealId: String): Flow<ViewMealDetail> = flow {
        val response = RetrofitBuilder.api.getMealDetail(mealId)
        emit(response)
    }.flowOn(Dispatchers.IO)
}