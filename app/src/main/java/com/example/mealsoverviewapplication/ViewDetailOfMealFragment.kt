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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.adapters.IngredientsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentViewDetailOfMealBinding
import com.example.mealsoverviewapplication.models.Ingredient
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.ViewMealDetailViewModel
import com.google.firebase.database.*

class ViewDetailOfMealFragment : Fragment() {
    private val binding by lazy {
        FragmentViewDetailOfMealBinding.inflate(layoutInflater)
    }
    private val ingredientAdapter by lazy {
        IngredientsAdapter()
    }
    private val viewMealDetailViewModel by viewModels<ViewMealDetailViewModel>()
    private var check =""

    private var viewMealDetailList: ArrayList<MealDetail> = arrayListOf()
    private var timeStamp: String = ""
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

    }
    private fun initCheck() {
        val mealId = args.mealId
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(mealId).addValueEventListener(object : ValueEventListener {
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

    private fun initAddFavourite(viewMealDetailList: ArrayList<MealDetail>) {

        binding.imgBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.tvAddToMyList.setOnClickListener { data ->
            data.isVisible = false
            binding.tvAddedToMyList.isVisible = true
            check = "true"
            insertData(viewMealDetailList)
        }

        binding.tvAddedToMyList.setOnClickListener { data ->
            data.isVisible = false
            binding.tvAddToMyList.isVisible = true
            check = "false"
            removeData()
        }
    }

    private fun removeData() {
        val mealId = args.mealId
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(mealId).removeValue()
            .addOnSuccessListener {

            }
    }
    private fun initObserver() {
        val mealId = args.mealId
        viewMealDetailViewModel.getViewMealDetail(mealId)
        viewMealDetailViewModel.responseLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                viewMealDetailList.addAll(data)
                initViewDetail(viewMealDetailList)
                initAddFavourite(viewMealDetailList)
                initViewIngredients(viewMealDetailList)
            }
        })
    }
    private fun initViewIngredients(viewMealDetailList: ArrayList<MealDetail>) {
        val ingredientList : ArrayList<Ingredient> = arrayListOf()
        val viewMealDetail: MealDetail? = viewMealDetailList.firstOrNull()
        val igd1 = viewMealDetail?.strIngredient1.toString()
        val igd2 = viewMealDetail?.strIngredient2.toString()
        val igd3 = viewMealDetail?.strIngredient3.toString()
        val igd4 = viewMealDetail?.strIngredient4.toString()
        val igd5 = viewMealDetail?.strIngredient5.toString()
        val igd6 = viewMealDetail?.strIngredient6.toString()
        val igd7 = viewMealDetail?.strIngredient7.toString()
        val igd8 = viewMealDetail?.strIngredient8.toString()
        val igd9 = viewMealDetail?.strIngredient9.toString()
        val igd10 = viewMealDetail?.strIngredient10.toString()
        val igd11 = viewMealDetail?.strIngredient11.toString()
        val igd12 = viewMealDetail?.strIngredient12.toString()
        val igd13 = viewMealDetail?.strIngredient13.toString()
        val igd14 = viewMealDetail?.strIngredient14.toString()
        val igd15 = viewMealDetail?.strIngredient15.toString()
        val igd16 = viewMealDetail?.strIngredient16.toString()
        val igd17 = viewMealDetail?.strIngredient17.toString()
        val igd18 = viewMealDetail?.strIngredient18.toString()
        val igd19 = viewMealDetail?.strIngredient19.toString()
        val igd20 = viewMealDetail?.strIngredient20.toString()

        val ms1 = viewMealDetail?.strMeasure1.toString()
        val ms2 = viewMealDetail?.strMeasure2.toString()
        val ms3 = viewMealDetail?.strMeasure3.toString()
        val ms4 = viewMealDetail?.strMeasure4.toString()
        val ms5 = viewMealDetail?.strMeasure5.toString()
        val ms6 = viewMealDetail?.strMeasure6.toString()
        val ms7 = viewMealDetail?.strMeasure7.toString()
        val ms8 = viewMealDetail?.strMeasure8.toString()
        val ms9 = viewMealDetail?.strMeasure9.toString()
        val ms10 = viewMealDetail?.strMeasure10.toString()
        val ms11 = viewMealDetail?.strMeasure11.toString()
        val ms12 = viewMealDetail?.strMeasure12.toString()
        val ms13 = viewMealDetail?.strMeasure13.toString()
        val ms14 = viewMealDetail?.strMeasure14.toString()
        val ms15 = viewMealDetail?.strMeasure15.toString()
        val ms16 = viewMealDetail?.strMeasure16.toString()
        val ms17 = viewMealDetail?.strMeasure17.toString()
        val ms18 = viewMealDetail?.strMeasure18.toString()
        val ms19 = viewMealDetail?.strMeasure19.toString()
        val ms20 = viewMealDetail?.strMeasure20.toString()

        if (igd1.isNotEmpty()){
            ingredientList.add(Ingredient(igd1,ms1))
        }
        if (igd2.isNotEmpty()){
            ingredientList.add(Ingredient(igd2,ms2))
        }
        if (igd3.isNotEmpty()){
            ingredientList.add(Ingredient(igd3,ms3))
        }
        if (igd4.isNotEmpty()){
            ingredientList.add(Ingredient(igd4,ms4))
        }
        if (igd5.isNotEmpty()){
            ingredientList.add(Ingredient(igd5,ms5))
        }
        if (igd6.isNotEmpty()){
            ingredientList.add(Ingredient(igd6,ms6))
        }
        if (igd7.isNotEmpty()){
            ingredientList.add(Ingredient(igd7,ms7))
        }
        if (igd8.isNotEmpty()){
            ingredientList.add(Ingredient(igd8,ms8))
        }
        if (igd9.isNotEmpty()){
            ingredientList.add(Ingredient(igd9,ms9))
        }
        if (igd10.isNotEmpty()){
            ingredientList.add(Ingredient(igd10,ms10))
        }
        if (igd11.isNotEmpty()){
            ingredientList.add(Ingredient(igd11,ms11))
        }
        if (igd12.isNotEmpty()){
            ingredientList.add(Ingredient(igd12,ms12))
        }
        if (igd13.isNotEmpty()){
            ingredientList.add(Ingredient(igd13,ms13))
        }
        if (igd14.isNotEmpty()){
            ingredientList.add(Ingredient(igd14,ms14))
        }
        if (igd15.isNotEmpty()){
            ingredientList.add(Ingredient(igd15,ms15))
        }
        if (igd16.isNotEmpty()){
            ingredientList.add(Ingredient(igd16,ms16))
        }
        if (igd17.isNotEmpty()){
            ingredientList.add(Ingredient(igd17,ms17))
        }
        if (igd18.isNotEmpty()){
            ingredientList.add(Ingredient(igd18,ms18))
        }
        if (igd19.isNotEmpty()){
            ingredientList.add(Ingredient(igd19,ms19))
        }
        if (igd20.isNotEmpty()){
            ingredientList.add(Ingredient(igd20,ms20))
        }
//        val ingredientList: ArrayList<Ingredient> = arrayListOf(Ingredient(igd1,ms1),Ingredient(igd2,ms2),
//            Ingredient(igd3,ms3), Ingredient
//        (igd4,ms4), Ingredient(igd5,ms5),Ingredient
//        (igd6,ms6), Ingredient
//        (igd7,ms7), Ingredient
//        (igd8,ms8), Ingredient
//        (igd9,ms9), Ingredient
//        (igd10,ms10), Ingredient
//        (igd11,ms11), Ingredient
//        (igd12,ms12), Ingredient
//        (igd13,ms13), Ingredient
//        (igd14, ms14),Ingredient
//        (igd15,ms15), Ingredient
//        (igd16,ms16), Ingredient
//        (igd17,ms17), Ingredient
//        (igd18, ms18), Ingredient
//        (igd19,ms19), Ingredient
//        (igd20,ms20)
//        )
        ingredientAdapter.setData(ingredientList)
        binding.ingredientRec.layoutManager = LinearLayoutManager(context)
        binding.ingredientRec.adapter = ingredientAdapter
    }

    private fun initViewDetail(viewMealDetailList: ArrayList<MealDetail>) {
        val viewMealDetail: MealDetail? = viewMealDetailList.firstOrNull()
        binding.tvMealDetail.text = viewMealDetail?.strMeal
        Glide.with(binding.imgThumb).load(viewMealDetail?.strMealThumb).into(binding.imgThumb)
        binding.tvStep.text = viewMealDetail?.strInstructions
    }

    private fun insertData(viewMealDetailList: ArrayList<MealDetail>) {
        val check = "true"
        timeStamp = System.currentTimeMillis().toString()
        val viewMealDetail : MealDetail? = viewMealDetailList.firstOrNull()
        val strMeal = viewMealDetail?.strMeal
        val idMeal = viewMealDetail?.idMeal
        val strMealThumb = viewMealDetail?.strMealThumb

        val hashMap: HashMap<String, String> = HashMap()
        hashMap[Constants.MEAL_ID] = idMeal.toString()
        hashMap[Constants.STR_MEAL] = strMeal.toString()
        hashMap[Constants.STR_THUMB] = strMealThumb.toString()
        hashMap[Constants.TIMESTAMP] = timeStamp
        hashMap[Constants.CHECK]= check

        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.child(idMeal.toString()).setValue(hashMap)
            .addOnSuccessListener {
            }
            .addOnFailureListener { e ->
                Log.d("check_false", "insertData: "+ e.message)
            }
    }
}



