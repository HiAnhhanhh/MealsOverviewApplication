package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.adapters.FavouriteMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentFavouriteListMealsBinding
import com.example.mealsoverviewapplication.models.MealDetail
import com.google.firebase.database.*


class FavouriteListMealsFragment : Fragment() {

    private val binding by lazy {
        FragmentFavouriteListMealsBinding.inflate(layoutInflater)
    }

    private val _favouriteAdapter by lazy {
        FavouriteMealsAdapter()
    }

    private var favouriteListMeals: ArrayList<MealDetail> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initView()
        initAction()

    }

    private fun initAction() {
        binding.imgBackBtn.setOnClickListener {
            findNavController().popBackStack()
        }
        _favouriteAdapter.setOnItemClickListener(object : FavouriteMealsAdapter.OnItemClickListener{
            override fun onItemClick(data:MealDetail, position: Int) {
                val mealId = data.idMeal
                val direction = FavouriteListMealsFragmentDirections.favoriteListMealsFragmentActionToViewDetailOfMealFragment(mealId)
                findNavController().navigate(direction)
            }
        })
    }
    private fun initView() {
        binding.favoriteRec.layoutManager = LinearLayoutManager(context)
        binding.favoriteRec.adapter = _favouriteAdapter
    }


    private fun initData() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                favouriteListMeals.clear()
                if (snapshot.exists()){
                    for (favouriteSnapshot in snapshot.children){
                        val favouriteModel = favouriteSnapshot.getValue(MealDetail::class.java)
                        if (favouriteModel != null) {
                            favouriteListMeals.add(favouriteModel)
                        }
                    }
                    _favouriteAdapter.setData(favouriteListMeals)
                    Log.d("check_fvlist", "onDataChange: "+ favouriteListMeals)
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}