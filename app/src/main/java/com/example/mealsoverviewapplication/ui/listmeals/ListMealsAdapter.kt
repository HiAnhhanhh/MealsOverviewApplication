package com.example.mealsoverviewapplication.ui.listmeals

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel


class ListMealsAdapter : RecyclerView.Adapter<ListMealsAdapter.ViewHolder>() {
    private var _listMealsArrayList : ArrayList<MealDetailModel> = arrayListOf()
    private lateinit var mListener : OnItemClickListener

    fun clearData(){
        _listMealsArrayList.clear()
    }
    fun setData (data: ArrayList<MealDetailModel>){
        _listMealsArrayList.clear()
        _listMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick (data: MealDetailModel, position: Int)
        fun onClickFavorite(data: MealDetailModel, position: Int)
        fun onClickAddedFavorite(data: MealDetailModel, position: Int)
    }

    fun setOnItemClickListener (listener: OnItemClickListener){
        mListener = listener
    }

    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (data: MealDetailModel, position: Int, listener: OnItemClickListener){
            binding.apply {
                tvTitle.text = data.strMeal
                Glide.with(binding.imvDailyMeal).load(data.strThumb).into(binding.imvDailyMeal)
            }

            binding.root.setOnClickListener {
                listener.onItemClick(data, position)
            }

            binding.imgFavorite.setOnClickListener {
                binding.imgFavorite.isVisible = false
                binding.imgAddedFavorite.isVisible = true
                listener.onClickFavorite(data,position)
            }

            binding.imgAddedFavorite.setOnClickListener {
                binding.imgAddedFavorite.isVisible = false
                binding.imgFavorite.isVisible = true
                listener.onClickAddedFavorite(data,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemDailyMealBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _listMealsArrayList.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = _listMealsArrayList[position]
        holder.bind(meal, position, mListener)
    }
}