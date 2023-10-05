package com.example.mealsoverviewapplication.ui.listmeals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.ui.utils.Constants
import com.example.mealsoverviewapplication.databinding.FragmentListMealsBinding
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ListMealsFragment : Fragment() {

    private val listMealsViewModel by viewModels<ListMealsViewModel> ()

    private val binding by lazy {
        FragmentListMealsBinding.inflate(layoutInflater)
    }

    private val listMealsAdapter by lazy {
        ListMealsAdapter()
    }

    private val args : ListMealsFragmentArgs by navArgs()

    private var listMeals : ArrayList<MealDetailModel> = arrayListOf()

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
        setAnim()

    }

    private fun setAnim() {

    }

    private fun initAction() {
        binding.imgBackStack.setOnClickListener {
            findNavController().popBackStack()
        }

        listMealsAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(data: MealDetailModel, position: Int) {
                val mealId = data.idMeal
                val directions = ListMealsFragmentDirections.listMealsFragmentActionViewDetailOfMealFragment(mealId.toString())
                findNavController().navigate(directions)
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
    private fun initView() {
        binding.shimmerListView.isVisible = true
        binding.shimmerListView.startShimmer()
        binding.listMealRec.layoutManager = LinearLayoutManager(context)
        binding.listMealRec.adapter = listMealsAdapter
        binding.listMealRec.isVisible = true
    }

    private fun initObserver() {
        val category = args.category
        listMealsViewModel.getListMeals(category)
        listMealsViewModel.responseLiveData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                listMeals.addAll(data as ArrayList<MealDetailModel>)
                listMealsAdapter.setData(listMeals)
                binding.shimmerListView.isVisible = false
            }
        }
    }
}