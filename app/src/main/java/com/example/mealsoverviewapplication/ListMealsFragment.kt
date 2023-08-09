package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.adapters.ListMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentListMealsBinding
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.viewmodels.ListMealsViewModel
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

    var listMeals : ArrayList<Meal> = arrayListOf()

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

    private fun initAction() {
        binding.imgBackStack.setOnClickListener {
            findNavController().popBackStack()
        }

        listMealsAdapter.setOnItemClickListener(object : ListMealsAdapter.onItemClickListener{
            override fun onItemClick(data: Meal, position: Int) {
                val mealId = data.idMeal
                val directions = ListMealsFragmentDirections.listMealsFragmentActionViewDetailOfMealFragment(mealId)
                findNavController().navigate(directions)
            }

            override fun onClickFavorite(data:Meal, position: Int) {
                val check = "true"
                val timeStamp = System.currentTimeMillis().toString()
                val mealId = data.idMeal
                val strThumb = data.strMealThumb
                val strMeal = data.strMeal

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

            override fun onClickAddedFavorite(data:Meal, position: Int) {
                val mealId = data.idMeal
                val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
                ref.child(mealId).removeValue()
                    .addOnSuccessListener {
                    }
            }
        })
    }
    private fun initView() {
        binding.listMealRec.layoutManager = LinearLayoutManager(context)
        binding.listMealRec.adapter = listMealsAdapter
    }

    private fun initObserver() {
        val category = args.category
        listMealsViewModel.getListMeals(category)
        listMealsViewModel.responseLiveData.observe(viewLifecycleOwner, Observer { data ->
            if( data != null){
                listMeals.addAll(data as ArrayList<Meal>)
                Log.d("check_listMeals", "initObserver: "+ listMeals)
                listMealsAdapter.setData(listMeals )
            }
        })
    }
}