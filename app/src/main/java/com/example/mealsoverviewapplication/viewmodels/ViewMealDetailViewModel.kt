package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.mapper.IngredientModel
import com.example.mealsoverviewapplication.mapper.MealDetailModel
import com.example.mealsoverviewapplication.mapper.toModel
import com.example.mealsoverviewapplication.mapper.toModelIngredient
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ViewMealDetailViewModel : ViewModel() {
    val  responseLiveData : MutableLiveData<List<IngredientModel>?> = MutableLiveData()
    val repository = Repositories()
    fun getViewMealDetail (mealId: String) {
        viewModelScope.launch {
            repository.getViewMealDetail(mealId)
                .catch { e ->
                    Log.d("check_viewDetail", "getViewMealDetail: ")
                }
                .collect { data ->
                    responseLiveData.value = data.meals?.map { item ->
                        item.toModelIngredient()
                    }
                }

        }
    }
}