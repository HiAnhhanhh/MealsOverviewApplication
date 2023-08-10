package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.mapper.MealDetailModel

class FilterMealsAdapter : RecyclerView.Adapter<FilterMealsAdapter.ItemMealsViewHolder>() {

    private var _filterMealsArrayList : ArrayList<MealDetailModel> = arrayListOf()
    private lateinit var mListener : OnItemClickListener


    fun setData (data: ArrayList<MealDetailModel>){
        _filterMealsArrayList.clear()
        _filterMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick (data: MealDetailModel, position: Int)
    }

    fun setOnItemClickListener (listener: OnItemClickListener){
        mListener = listener
    }

    class ItemMealsViewHolder(private val binding : ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView (data: MealDetailModel, position: Int, listener: OnItemClickListener){
            binding.apply {
                tvTitle.text = data.strMeal
                Glide.with(binding.imvDailyMeal).load(data.strThumb).into(binding.imvDailyMeal)
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
        holder.bindView(filterMeal, position, mListener)
    }
}