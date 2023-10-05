package com.example.mealsoverviewapplication.ui.favouritemeals

import com.example.mealsoverviewapplication.data.mapper.MealDetailModel

interface OnItemClickListener {
    fun onItemClick (data: MealDetailModel, position: Int)

}