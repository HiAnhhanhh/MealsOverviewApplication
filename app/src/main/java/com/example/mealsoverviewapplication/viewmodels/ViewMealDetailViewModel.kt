package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.repository.ViewMealDetailRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ViewMealDetailViewModel : ViewModel() {
    val  responseLiveData : MutableLiveData<List<MealDetail>?> = MutableLiveData()

    fun getViewMealDetail (mealId: String) {
        viewModelScope.launch {
            ViewMealDetailRepository.getViewMealDetail(mealId)
                .catch { e ->
                    Log.d("check_viewDetail", "getViewMealDetail: ")
                }
                .collect { data ->
                    responseLiveData.value = data.meals
                }

        }
    }
}