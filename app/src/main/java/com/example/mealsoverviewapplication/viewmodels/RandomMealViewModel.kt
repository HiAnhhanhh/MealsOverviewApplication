package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.RandomMeal
import com.example.mealsoverviewapplication.repository.RandomMealRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RandomMealViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<Meal>?> = MutableLiveData()

    fun getRandomMeal (){
        viewModelScope.launch {
            RandomMealRepository.getRandomMeal()
                .catch { e ->
                    Log.d("check_randomMeal", "getRandomMeal: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.meals
                }
        }
    }
}