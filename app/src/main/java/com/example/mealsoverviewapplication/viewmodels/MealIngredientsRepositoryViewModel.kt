package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.repository.MealIngredientsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class MealIngredientsRepositoryViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<Meal>?> = MutableLiveData()

    fun getMealIngredients () {
        viewModelScope.launch {
            MealIngredientsRepository.getRandomMeals()
                .catch { e ->
                    Log.d("check_data_1", "getMealIngredients 1: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.meals
                }
        }
    }
}