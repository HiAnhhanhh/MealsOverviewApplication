package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.databinding.FragmentViewDetailOfMealBinding
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.MealIngredientsRepositoryViewModel
import com.google.firebase.database.*

class ViewDetailOfMealFragment : Fragment() {
    private val binding by lazy {
        FragmentViewDetailOfMealBinding.inflate(layoutInflater)
    }
    private val mealIngredientsViewModel by viewModels<MealIngredientsRepositoryViewModel>()
    var check =""

    private var mealIngredients: ArrayList<Meal> = arrayListOf()
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

        initObserver()
        initCheck()
        initData()
        initView()
        initAction()


    }

    private fun initCheck() {
        val mealDetail = args.mealDetail
        val categoryId = mealDetail.strCategoryId
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(categoryId).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val check = snapshot.child("check").value
                if (check == "true"){
                    binding.tvAddToMyList.isVisible = false
                    binding.tvAddedToMyList.isVisible = true
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun initView() {
        getMealIngredients(mealIngredients)
    }

    private fun initData() {
        val mealDetail = args.mealDetail
        loadData(mealDetail)
    }


    private fun initAction() {
        val mealDetail = args.mealDetail

        binding.imgBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvAddToMyList.setOnClickListener { data ->
            data.isVisible = false
            binding.tvAddedToMyList.isVisible = true
            check = "true"
            insertData(mealDetail)
        }

        binding.tvAddedToMyList.setOnClickListener { data ->
            data.isVisible = false
            binding.tvAddToMyList.isVisible = true
            check = "false"
            removeData()
        }
    }

    private fun removeData() {
        val mealDetail = args.mealDetail
        val categoryId = mealDetail.strCategoryId
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(categoryId).removeValue()
            .addOnSuccessListener {

            }
    }
    private fun initObserver() {
        mealIngredientsViewModel.getMealIngredients()
        mealIngredientsViewModel.responseLiveData.observe(viewLifecycleOwner, Observer { data ->
            mealIngredients.addAll(data as ArrayList<Meal>)
            getMealIngredients(mealIngredients)
        })
    }
    private fun getMealIngredients(mealIngredients: ArrayList<Meal>) {
        val mealIngredient: Meal? = mealIngredients.firstOrNull()
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

    private fun insertData(mealDetail: MealDetail) {
        timeStamp = System.currentTimeMillis().toString()
        val categoryId = mealDetail.strCategoryId
        val strCategory = mealDetail.strCategory
        val strCategoryThumb = mealDetail.strCategoryThumb
        val strDes = mealDetail.description
        val hashMap: HashMap<String, Any> = HashMap()
        hashMap["strCategoryId"] = categoryId
        hashMap["strCategory"] = strCategory
        hashMap["strCategoryThumb"] = strCategoryThumb
        hashMap["description"] = strDes
        hashMap["timestamp"] = timeStamp
        hashMap["check"]= check

        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(categoryId).setValue(hashMap)
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



