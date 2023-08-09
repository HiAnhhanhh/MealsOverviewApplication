package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.models.MealDetail

class FilterMealsAdapter : RecyclerView.Adapter<FilterMealsAdapter.ItemMealsViewHolder>() {

    private var _filterMealsArrayList : ArrayList<MealDetail> = arrayListOf()
    private lateinit var mlistener : onItemClickListener

    fun setData (data: ArrayList<MealDetail>){
        _filterMealsArrayList.clear()
        _filterMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    interface onItemClickListener {
        fun onItemClick (data: MealDetail, position: Int)
    }

    fun setOnItemClickListener (listener: onItemClickListener){
        mlistener = listener
    }

    class ItemMealsViewHolder(private val binding : ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView (data: MealDetail, position: Int, listener: onItemClickListener){
            binding.apply {
                tvTitle.text = data.strMeal
                tvDes.text = data.strInstructions
                Glide.with(binding.imvDailyMeal).load(data.strMealThumb).into(binding.imvDailyMeal)
            }

            binding.root.setOnClickListener {
                listener.onItemClick(data,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemMealsViewHolder {
        val binding = ItemDailyMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemMealsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _filterMealsArrayList.size
    }

    override fun onBindViewHolder(holder: ItemMealsViewHolder, position: Int) {
        val filterMeal = _filterMealsArrayList[position]
        holder.bindView(filterMeal, position, mlistener)
    }
}