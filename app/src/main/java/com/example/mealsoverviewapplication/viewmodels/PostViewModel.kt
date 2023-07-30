package com.example.mealsoverviewapplication.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.CategoryTypes
import com.example.mealsoverviewapplication.models.Post
import com.example.mealsoverviewapplication.repository.PostsRepository
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {
    val responseLiveData : MutableLiveData<List<Post>> = MutableLiveData()

    fun getPost () {
//        viewModelScope.launch {
//            PostsRepository.getPost()
//                .catch { e ->
//                    Log.d("main_check_post", "getPost:${e.message} ")
//                }
//                .collect { response ->
//                    Log.d("main_check_post", "getPost: "+ response)
//                    responseLiveData.value = response
//                }
//        }
    }

//    fun checkCategoryType(type: String): CategoryTypes {
//        return when(type) {
//            "pho" -> CategoryTypes.PHO
//            else -> {...}
//        }
//    }

}