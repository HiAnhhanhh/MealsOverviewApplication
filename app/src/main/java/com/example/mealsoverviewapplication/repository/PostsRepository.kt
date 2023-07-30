package com.example.mealsoverviewapplication.repository

import com.example.mealsoverviewapplication.apis.RetrofitBuilder
import com.example.mealsoverviewapplication.models.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class PostsRepository {

//    companion object {
//        fun getPost(): Flow<List<Post>> = flow {
//            val response = RetrofitBuilder.api.getPost()
//            emit(response)
//        }.flowOn(Dispatchers.IO)
//    }
}