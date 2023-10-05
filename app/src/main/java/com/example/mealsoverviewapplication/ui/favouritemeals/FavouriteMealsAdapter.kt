package com.example.mealsoverviewapplication.ui.favouritemeals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel

class FavouriteMealsAdapter  : RecyclerView.Adapter<FavouriteMealsAdapter.ViewHolder>() {

    private val _favouriteMealsArrayList: ArrayList<MealDetailModel> = arrayListOf()

    private lateinit var mListener : OnItemClickListener


    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    fun setData(data: ArrayList<MealDetailModel>){
        _favouriteMealsArrayList.clear()
        _favouriteMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }
    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (data: MealDetailModel, position: Int, listener: OnItemClickListener){
            binding.tvTitle.text = data.strMeal
            Glide.with(binding.imvDailyMeal).load(data.strThumb).into(binding.imvDailyMeal)

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
        holder.bind(favouriteMeal,position, mListener)
    }
}

