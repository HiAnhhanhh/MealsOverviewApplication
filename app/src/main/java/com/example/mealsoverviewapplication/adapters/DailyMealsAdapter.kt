package com.example.mealsoverviewapplication.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.Constants
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ItemDailyMealBinding
import com.example.mealsoverviewapplication.models.Category
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class DailyMealsAdapter : RecyclerView.Adapter<DailyMealsAdapter.ViewHolder>() {

    private val _dailyMealsArrayList: ArrayList<Category> = arrayListOf()
    private lateinit var mlistener : onItemClickListener
    interface onItemClickListener {
        fun onItemClick (data:Category, position: Int)
        fun onItemClickFavorite (data:Category, position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mlistener = listener
    }

    fun setData (data: ArrayList<Category>) {
        _dailyMealsArrayList.clear()
        _dailyMealsArrayList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemDailyMealBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindViewHolder (data : Category, position: Int, listener: onItemClickListener){
            binding.tvTitle.text = data.strCategory
            binding.tvDes.text = data.strCategoryDescription
            Glide.with(binding.imvDailyMeal).load(data.strCategoryThumb).into(binding.imvDailyMeal)

            binding.root.setOnClickListener {
                listener.onItemClick(data,position)
            }

            binding.imgFavorite.setOnClickListener {
                binding.imgFavorite.setImageResource(R.drawable.baseline_favorite_red_24)
                listener.onItemClickFavorite(data,position)
            }
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
        holder.bindViewHolder(dailyModel,position, mlistener)
    }
}