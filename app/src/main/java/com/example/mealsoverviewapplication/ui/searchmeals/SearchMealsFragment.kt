package com.example.mealsoverviewapplication.ui.searchmeals

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.ui.utils.Constants
import com.example.mealsoverviewapplication.databinding.FragmentSearchMealsBinding
import com.example.mealsoverviewapplication.ui.listmeals.ListMealsAdapter
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlin.collections.ArrayList

class SearchMealsFragment : Fragment() {

    private val binding by lazy {
        FragmentSearchMealsBinding.inflate(layoutInflater)
    }

    private val filterMealsViewModel by viewModels<FilterMealsViewModel>()

    private val filterMealsAdapter by lazy {
        FilterMealsAdapter()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserver()
        initView()
        initAction()
    }

    private fun initObserver() {
        filterMealsViewModel.responseLiveData.observe(viewLifecycleOwner) { data ->
            if (data != null){
                filterMealsAdapter.setData(data as ArrayList<MealDetailModel>)
            }
        }
    }
    private fun initView() {
        binding.recList.layoutManager = LinearLayoutManager(context)
        binding.recList.adapter = filterMealsAdapter
    }
    private fun initAction() {
        binding.searchViewFilterMeal.addTextChangedListener(object :TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                filterMealsList(s)
            }
        })
        filterMealsAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(data: MealDetailModel, position: Int) {
                val mealId = data.idMeal.toString()
                val direction = SearchMealsFragmentDirections.searchMealsFragmentActionToViewDetailOfMealFragment(mealId)
                findNavController().navigate(direction)
            }

            override fun onClickFavorite(data: MealDetailModel, position: Int) {
                val check = "true"
                val timeStamp = System.currentTimeMillis().toString()
                val mealId = data.idMeal.toString()
                val strThumb = data.strThumb.toString()
                val strMeal = data.strMeal.toString()

                val hashMap: HashMap<String, String> = HashMap()
                hashMap[Constants.MEAL_ID] = mealId
                hashMap[Constants.STR_MEAL] = strMeal
                hashMap[Constants.STR_THUMB] = strThumb
                hashMap[Constants.TIMESTAMP] = timeStamp
                hashMap[Constants.CHECK]= check

                val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
                ref.child(mealId).setValue(hashMap)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener { e ->
                        Log.d("check_false", "insertData: "+ e.message)
                    }
            }

            override fun onClickAddedFavorite(data: MealDetailModel, position: Int) {
                val mealId = data.idMeal.toString()
                val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
                ref.child(mealId).removeValue()
                    .addOnSuccessListener {
                    }
            }
        })
    }
    private fun filterMealsList(s: Editable?) {
        if (!s.isNullOrEmpty()){
            filterMealsViewModel.getFilterMeals(s)
            binding.recList.isVisible = true
        } else{
            binding.recList.isVisible = false
            filterMealsAdapter.clearData()
        }
    }
}


