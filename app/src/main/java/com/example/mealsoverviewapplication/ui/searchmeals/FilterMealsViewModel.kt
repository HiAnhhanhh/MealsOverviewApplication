package com.example.mealsoverviewapplication.ui.searchmeals

import android.text.Editable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.example.mealsoverviewapplication.data.mapper.toModel
import com.example.mealsoverviewapplication.data.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class FilterMealsViewModel : ViewModel() {
    var responseLiveData : MutableLiveData<List<MealDetailModel>?> = MutableLiveData()
    private val repository = Repositories()

    fun getFilterMeals (letter: Editable?){
        viewModelScope.launch {
            repository.getFilterMeals(letter)
                .catch { e ->
                    Log.d("check_data_filterMeals", "getFilterMeals: ${e.message} ")
                }
                .collect { data ->
                    responseLiveData.value = data.meals?.map { item ->
                        item.toModel()
                    }
                }
        }
    }
}