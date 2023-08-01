package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.FragmentViewDetailOfMealBinding
import com.example.mealsoverviewapplication.models.MealDetail
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ViewDetailOfMealFragment : Fragment() {
    private val binding by lazy {
        FragmentViewDetailOfMealBinding.inflate(layoutInflater)
    }

    private val args: ViewDetailOfMealFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mealDetail = args.mealDetail



        loadData(mealDetail)

        binding.tvAddToMyList.setOnClickListener {
            it.isVisible = false
            binding.tvAddedToMyList.isVisible = true
            insertData(mealDetail)
        }

        binding.tvAddedToMyList.setOnClickListener {
            it.isVisible = false
            binding.tvAddToMyList.isVisible = true
            removeData()
        }

    }
    private fun removeData() {
    }
    private fun insertData(mealDetail: MealDetail) {
        val timestamp: String = System.currentTimeMillis().toString()
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["title"] = mealDetail.strCategory
        hashMap["thumb"] = mealDetail.strCategoryThumb
        hashMap["description"] = mealDetail.description
        hashMap["timestamp"] = timestamp

        Log.d("check_data", "insertData: oke"+ hashMap)

        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(timestamp).setValue(hashMap)
            .addOnSuccessListener {

            }
            .addOnFailureListener { e ->
                Log.d("check_false", "insertData: "+ e.message)
            }
    }
    private fun loadData(mealDetail: MealDetail) {
        binding.apply {
            binding.tvMealDetail.text = mealDetail.strCategory
            Glide.with(binding.imgThumb).load(mealDetail.strCategoryThumb).into(binding.imgThumb)
        }
    }
}