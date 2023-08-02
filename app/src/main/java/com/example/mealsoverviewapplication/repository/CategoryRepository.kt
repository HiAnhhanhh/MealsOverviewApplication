package com.example.mealsoverviewapplication.repository

import android.util.Log
import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.Categories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn



class CategoryRepository {
    companion object {
        fun getCategories(): Flow<Categories> = flow {
            val response =  RetrofitBuilder.api.getCategories()
            emit(response)
        }.flowOn(Dispatchers.IO)

    }
}


