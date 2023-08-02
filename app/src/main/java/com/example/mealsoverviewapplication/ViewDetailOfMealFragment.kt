package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.FragmentViewDetailOfMealBinding
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.MealIngredientsRepositoryViewModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ViewDetailOfMealFragment : Fragment() {
    private val binding by lazy {
        FragmentViewDetailOfMealBinding.inflate(layoutInflater)
    }
    private var mealIngredientsViewModel = MealIngredientsRepositoryViewModel()

    var mealIngredients: ArrayList<Meal> = arrayListOf()
    var timeStamp: String = ""

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
        initData()

        binding.imgBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

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

        binding.imgBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun getMealIngredients(mealIngredients: ArrayList<Meal>) {
        val mealIngredient: Meal? = this.mealIngredients.firstOrNull()
//
        Log.d("check_data_1", "getMealIngredients: "+ mealIngredient)
        binding.tvIngredient1.text = mealIngredient?.strIngredient1
        binding.tvIngredient2.text = mealIngredient?.strIngredient2
        binding.tvIngredient3.text = mealIngredient?.strIngredient3
        binding.tvIngredient4.text = mealIngredient?.strIngredient4
        binding.tvIngredient5.text = mealIngredient?.strIngredient5
        binding.tvMeasure1.text = mealIngredient?.strMeasure1
        binding.tvMeasure2.text = mealIngredient?.strMeasure2
        binding.tvMeasure3.text = mealIngredient?.strMeasure3
        binding.tvMeasure4.text = mealIngredient?.strMeasure4
        binding.tvMeasure5.text = mealIngredient?.strMeasure5
        binding.tvStep.text = mealIngredient?.strInstructions

    }

    private fun initData() {
        mealIngredientsViewModel = ViewModelProvider(this)[MealIngredientsRepositoryViewModel::class.java]
        mealIngredientsViewModel.getMealIngredients()
        mealIngredientsViewModel.responseLiveData.observe(viewLifecycleOwner, Observer {
            mealIngredients.addAll(it as ArrayList<Meal>)
            getMealIngredients(mealIngredients)
        })
    }

    private fun removeData() {

    }
    private fun insertData(mealDetail: MealDetail) {
        timeStamp = System.currentTimeMillis().toString()
        val hashMap: HashMap<String, String> = HashMap()
        hashMap["strCategory"] = mealDetail.strCategory
        hashMap["strCategoryThumb"] = mealDetail.strCategoryThumb
        hashMap["description"] = mealDetail.description
        hashMap["timestamp"] = timeStamp

        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(timeStamp).setValue(hashMap)
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



