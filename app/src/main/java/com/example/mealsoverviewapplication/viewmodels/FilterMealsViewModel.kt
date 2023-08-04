package com.example.mealsoverviewapplication.viewmodels

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.repository.FilterMealsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FilterMealsViewModel : ViewModel() {
    var responseLiveData : MutableLiveData<List<Meal>?> = MutableLiveData()

    fun getFilterMeals (letter: Editable?){
        viewModelScope.launch {
            FilterMealsRepository.getFilterMeals(letter)
                .catch { e ->
                    Log.d("check_data_filterMeals", "getFilterMeals: ")
                }
                .collect { data ->
                    responseLiveData.value = data.meals
                }
        }
    }
}