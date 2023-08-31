package com.example.mealsoverviewapplication.ui.dailymeals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.data.mapper.CategoryModel

class DailyMealsAdapter : RecyclerView.Adapter<DailyMealsAdapter.ViewHolder>() {

    private val _dailyMealsArrayList: ArrayList<CategoryModel> = arrayListOf()
    private lateinit var mListener : OnItemClickListener
    interface OnItemClickListener {
        fun onItemClick (data: CategoryModel, position: Int)
    }
    fun setOnItemClickListener(listener: OnItemClickListener){
        mListener = listener
    }

    fun setData (data: ArrayList<CategoryModel>) {
        _dailyMealsArrayList.clear()
        _dailyMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder (data : CategoryModel, position: Int, listener: OnItemClickListener){
            binding.tvTitle.text = data.strCategory
            binding.tvDes.text = data.strCategoryDes
            Glide.with(binding.imvDailyMeal).load(data.strCategoryThumb).into(binding.imvDailyMeal)

            binding.root.setOnClickListener {
                listener.onItemClick(data,position)
            }
            binding.imgFavorite.isVisible = false
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =ItemDailyMealBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _dailyMealsArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dailyModel = _dailyMealsArrayList[position]
        holder.bindViewHolder(dailyModel,position, mListener)
    }
}

