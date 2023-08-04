package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.models.Meal

class FilterMealsAdapter : RecyclerView.Adapter<FilterMealsAdapter.ItemMealsViewHolder>() {

    var _filterMealsArrayList : ArrayList<Meal> = arrayListOf()
    private lateinit var mlistener : onItemClickListener

    fun setData (data: ArrayList<Meal>){
        _filterMealsArrayList.clear()
        _filterMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    interface onItemClickListener {
        fun onItemClick (position: Int)
    }

    fun setOnItemClickListener (listener: onItemClickListener){
        mlistener = listener
    }

    class ItemMealsViewHolder(private val binding : ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView (data: Meal, position: Int, listener: onItemClickListener){
            binding.apply {
                tvTitle.text = data.strMeal
                tvDes.text = data.strInstructions
                Glide.with(binding.imvDailyMeal).load(data.strMealThumb).into(binding.imvDailyMeal)
            }

            binding.root.setOnClickListener {
                listener.onItemClick(position)
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