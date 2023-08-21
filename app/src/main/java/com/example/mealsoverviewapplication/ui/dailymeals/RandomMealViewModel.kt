package com.example.mealsoverviewapplication.ui.dailymeals

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.example.mealsoverviewapplication.data.mapper.toModel
import com.example.mealsoverviewapplication.data.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class RandomMealViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<MealDetailModel>?> = MutableLiveData()
    private val repository = Repositories()
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