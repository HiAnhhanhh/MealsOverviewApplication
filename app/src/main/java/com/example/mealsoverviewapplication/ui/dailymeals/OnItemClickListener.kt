package com.example.mealsoverviewapplication.ui.dailymeals

import com.example.mealsoverviewapplication.data.mapper.CategoryModel

interface OnItemClickListener {
    fun onItemClick (data: CategoryModel, position: Int)
}