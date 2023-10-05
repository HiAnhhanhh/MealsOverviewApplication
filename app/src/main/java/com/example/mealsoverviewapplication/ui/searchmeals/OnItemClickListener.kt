package com.example.mealsoverviewapplication.ui.searchmeals

import com.example.mealsoverviewapplication.data.mapper.MealDetailModel

interface OnItemClickListener {
    fun onItemClick (data: MealDetailModel, position: Int)
    fun onClickFavorite(data: MealDetailModel, position: Int)
    fun onClickAddedFavorite(data: MealDetailModel, position: Int)
}