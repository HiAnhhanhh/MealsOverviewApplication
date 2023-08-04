package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.CategoryTypes
import com.example.mealsoverviewapplication.repository.CategoryRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch


class DailyMealsViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<Category>?> = MutableLiveData()

    fun getCategory () {
        viewModelScope.launch {
            CategoryRepository.getCategories()
                .catch {
                        e ->
                    Log.d("check_data", "getCategories: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.categories
                }
        }
    }
    fun checkCategoryType(type: String): CategoryTypes {
        return when(type) {
            "pho" -> CategoryTypes.PHO
            else -> {CategoryTypes.GA}
        }
    }
}


