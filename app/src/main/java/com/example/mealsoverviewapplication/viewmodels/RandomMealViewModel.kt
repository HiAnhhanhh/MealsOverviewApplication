package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.mapper.MealDetailModel
import com.example.mealsoverviewapplication.mapper.toModel
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RandomMealViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<MealDetailModel>?> = MutableLiveData()
    val repository = Repositories()
    fun getRandomMeal (){
        viewModelScope.launch {
            repository.getRandomMeal()
                .catch { e ->
                    Log.d("check_randomMeal", "getRandomMeal: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.meals?.map { item ->
                        item.toModel()
                    }
                }
        }
    }
}