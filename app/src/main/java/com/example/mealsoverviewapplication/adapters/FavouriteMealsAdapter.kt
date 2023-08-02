package com.example.mealsoverviewapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.models.MealDetail

class FavouriteMealsAdapter () : RecyclerView.Adapter<FavouriteMealsAdapter.ViewHolder>() {

    val _favouriteMealsArrayList: ArrayList<MealDetail> = arrayListOf()

    fun setData(data: ArrayList<MealDetail>){
        _favouriteMealsArrayList.clear()
        _favouriteMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (data: MealDetail, position: Int){
            binding.tvTitle.text = data.strCategory
            binding.tvDes.text = data.description
            Glide.with(binding.imvDailyMeal).load(data.strCategoryThumb).into(binding.imvDailyMeal)
            Log.d("load_image", "bind: "+ data.strCategoryThumb+ data.strCategory)
            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_red_24)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDailyMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _favouriteMealsArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val favouriteMeal = _favouriteMealsArrayList[position]
        holder.bind(favouriteMeal, position)
    }
}

