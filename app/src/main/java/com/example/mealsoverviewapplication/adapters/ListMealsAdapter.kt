package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.models.Meal


class ListMealsAdapter : RecyclerView.Adapter<ListMealsAdapter.ViewHolder>() {
    private var _listMealsArrayList : ArrayList<Meal> = arrayListOf()
    private lateinit var mlistener : onItemClickListener

    fun setData (data: ArrayList<Meal>){
        _listMealsArrayList.clear()
        _listMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    interface onItemClickListener {
        fun onItemClick (data: Meal, position: Int)
        fun onClickFavorite(data: Meal, position: Int)
        fun onClickAddedFavorite(data: Meal, position: Int)
    }

    fun setOnItemClickListener (listener: onItemClickListener){
        mlistener = listener
    }

    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind (data: Meal, position: Int, listener:onItemClickListener){
            binding.apply {
                tvTitle.text = data.strMeal
                Glide.with(binding.imvDailyMeal).load(data.strMealThumb).into(binding.imvDailyMeal)
            }

            binding.root.setOnClickListener {
                listener.onItemClick(data, position)
            }

            binding.imgFavorite.setOnClickListener {
                binding.imgFavorite.isVisible = false
                binding.imgAddedFavorite.isVisible = true
            }

            binding.imgAddedFavorite.setOnClickListener {
                binding.imgAddedFavorite.isVisible = false
                binding.imgFavorite.isVisible = true
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
        holder.bind(meal, position, mlistener)
    }
}