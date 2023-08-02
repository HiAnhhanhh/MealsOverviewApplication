package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsoverviewapplication.databinding.ItemCheckboxBinding
import com.example.mealsoverviewapplication.models.Meal

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    
    val _ingredientArrayList : ArrayList<Meal> = arrayListOf()
    
    fun setData (data: ArrayList<Meal>){
        _ingredientArrayList.clear()
        _ingredientArrayList.addAll(data)
//        notifyDataSetChanged()
    }
    
    class ViewHolder(private val binding: ItemCheckboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindView (data : Meal) {
            binding.apply { 
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCheckboxBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return _ingredientArrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        
    }
}