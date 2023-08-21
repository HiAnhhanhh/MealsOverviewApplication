package com.example.mealsoverviewapplication.ui.listmeals

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.example.mealsoverviewapplication.data.mapper.toModel
import com.example.mealsoverviewapplication.data.repository.Repositories
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class ListMealsViewModel : ViewModel() {

    val responseLiveData : MutableLiveData<List<MealDetailModel>?> = MutableLiveData()
    private val repository = Repositories()

//    fun onFavoriteItem(id: String) {
//        val oldData = responseLiveData
//        oldData.map { item ->
//            //call api doi state // update lai trong database
//            //
//        }
//    }

    fun getListMeals (category: String){
        viewModelScope.launch {
            repository.getListMeals(category)
                .catch { e ->
                    Log.d("check_listMeals", "getListMeals: ${e.message}")
                }
                .collect { data ->
                    responseLiveData.value = data.meals?.map { item ->
                        item.toModel()
                    }
                }
        }
    }
}