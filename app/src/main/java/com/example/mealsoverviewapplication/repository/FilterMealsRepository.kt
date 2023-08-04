package com.example.mealsoverviewapplication.repository

import android.text.Editable
import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.ListFilterMeals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class FilterMealsRepository {
    companion object {
        fun getFilterMeals(letter: Editable?): Flow<ListFilterMeals> = flow {
            val response = RetrofitBuilder.api.getFilterMeals(letter)
            emit(response)
        }.flowOn(Dispatchers.IO)
    }
}