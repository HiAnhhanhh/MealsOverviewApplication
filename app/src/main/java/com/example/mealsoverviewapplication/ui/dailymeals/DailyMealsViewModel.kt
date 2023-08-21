package com.example.mealsoverviewapplication.ui.dailymeals

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.data.mapper.CategoryModel
import com.example.mealsoverviewapplication.data.mapper.toModel
import com.example.mealsoverviewapplication.data.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class DailyMealsViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<CategoryModel>?> = MutableLiveData()
    private val repository = Repositories()
    fun getCategory () {
        viewModelScope.launch {
            repository.getCategories()
                .catch {
                        e ->
                    Log.d("check_data", "getCategories: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.categories?.map { item ->
                        item.toModel()
                    }
                }
        }
    }
}


