package com.example.mealsoverviewapplication.viewmodels

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FilterMealsViewModel : ViewModel() {
    var responseLiveData : MutableLiveData<List<MealDetail>?> = MutableLiveData()
    val repository = Repositories()

    fun getFilterMeals (letter: Editable?){
        viewModelScope.launch {
            repository.getFilterMeals(letter)
                .catch { e ->
                    Log.d("check_data_filterMeals", "getFilterMeals: ${e.message} ")
                }
                .collect { data ->
                    responseLiveData.value = data.meals
                }
        }
    }
}