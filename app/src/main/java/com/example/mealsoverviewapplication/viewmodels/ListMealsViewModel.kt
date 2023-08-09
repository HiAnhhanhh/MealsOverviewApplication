package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.repository.ListMealsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListMealsViewModel : ViewModel() {

    val responseLiveData : MutableLiveData<List<Meal>?> = MutableLiveData()

//    fun onFavoriteItem(id: String) {
//        val oldData = responseLiveData
//        oldData.map { item ->
//            //call api doi state // update lai trong database
//            //
//        }
//    }

    fun getListMeals (category: String){
        viewModelScope.launch {
            ListMealsRepository.getListMeals(category)
                .catch { e ->
                    Log.d("check_listMeals", "getListMeals: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.meals
                }
        }
    }
}