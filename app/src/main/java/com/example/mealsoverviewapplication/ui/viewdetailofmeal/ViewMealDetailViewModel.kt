package com.example.mealsoverviewapplication.ui.viewdetailofmeal

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.data.mapper.IngredientModel
import com.example.mealsoverviewapplication.data.mapper.toModelIngredient
import com.example.mealsoverviewapplication.data.repository.Repositories
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ViewMealDetailViewModel : ViewModel() {
    var responseLiveData : MutableLiveData<List<IngredientModel>?> = MutableLiveData()
    private set
//    private var mealDetailState = MutableStateFlow<List<IngredientModel>?>(listOf())
//    val mealDetailStateFlow = mealDetailState.asStateFlow()

    private val repository = Repositories()
    fun getViewMealDetail(mealId: String) {
        viewModelScope.launch {
            repository.getViewMealDetail(mealId)
                .catch { e ->
                    Log.d("check_viewDetail", "getViewMealDetail: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.meals?.map { item ->
                        item.toModelIngredient()
                    }
//                    mealDetailState.emit(data.meals?.map { item ->
//                        item.toModelIngredient()
//                    })
//                }
                }
        }
    }

}