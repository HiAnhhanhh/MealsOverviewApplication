package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.MealDetail

class FavouriteMealsAdapter  : RecyclerView.Adapter<FavouriteMealsAdapter.ViewHolder>() {

    private val _favouriteMealsArrayList: ArrayList<MealDetail> = arrayListOf()

    private lateinit var mlistener : onItemClickListener

    interface onItemClickListener {
        fun onItemClick (data: MealDetail, position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mlistener = listener
    }

    fun setData(data: ArrayList<MealDetail>){
        _favouriteMealsArrayList.clear()
        _favouriteMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (data: MealDetail, position: Int, listener:onItemClickListener){
            binding.tvTitle.text = data.strMeal
            Glide.with(binding.imvDailyMeal).load(data.strMealThumb).into(binding.imvDailyMeal)

            binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_red_24)
            binding.root.setOnClickListener {
                listener.onItemClick(data, position)
            }
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
        holder.bind(favouriteMeal,position, mlistener)
    }
}

