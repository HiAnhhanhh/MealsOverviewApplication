package com.example.mealsoverviewapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mealsoverviewapplication.databinding.ItemCheckboxBinding
import com.example.mealsoverviewapplication.models.Ingredient

class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    
    val _ingredientArrayList : ArrayList<Ingredient> = arrayListOf()
    
    fun setData (data: ArrayList<Ingredient>){
        _ingredientArrayList.clear()
        _ingredientArrayList.addAll(data)
        notifyDataSetChanged()
    }
    
    class ViewHolder(private val binding: ItemCheckboxBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (data : Ingredient) {
            binding.apply {
                binding.tvIngredient.text = data.Ingredient
                binding.tvMeasure.text = data.Measure
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
        val ingredient = _ingredientArrayList[position]
        holder.bind(ingredient)
    }
}