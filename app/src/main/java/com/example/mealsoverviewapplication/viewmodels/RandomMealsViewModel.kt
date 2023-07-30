package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.repository.RandomMealsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RandomMealsViewModel : ViewModel() {

    val responseLiveData : MutableLiveData<List<Meal>?> = MutableLiveData()

    fun getMeal() {
        viewModelScope.launch {
            RandomMealsRepository.getRandomMeals()
                .catch { e ->
                    Log.d("check_data", "getMeal: ${e.message} ")
                }
                .collect { response ->
                    responseLiveData.value= response.meals as List<Meal>?
                }
        }
    }
}