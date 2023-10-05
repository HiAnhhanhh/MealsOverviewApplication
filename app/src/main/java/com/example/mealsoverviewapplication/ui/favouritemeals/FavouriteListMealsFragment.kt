package com.example.mealsoverviewapplication.ui.favouritemeals

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.FragmentFavouriteListMealsBinding
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.google.firebase.database.*


class FavouriteListMealsFragment : Fragment() {

    private val binding by lazy {
        FragmentFavouriteListMealsBinding.inflate(layoutInflater)
    }

    private val _favouriteAdapter by lazy {
        FavouriteMealsAdapter()
    }

    private var favouriteListMeals: ArrayList<MealDetailModel> = arrayListOf()

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
        _favouriteAdapter.setOnItemClickListener(object : OnItemClickListener{
            override fun onItemClick(data: MealDetailModel, position: Int) {
                val mealId = data.idMeal.toString()
                val direction = FavouriteListMealsFragmentDirections.favoriteListMealsFragmentActionToViewDetailOfMealFragment(mealId)
                findNavController().navigate(direction)
            }
        })
    }

    private fun initView() {
        binding.shimmerFavouriteView.isVisible = true
        binding.shimmerFavouriteView.startShimmer()
        binding.favoriteRec.layoutManager = LinearLayoutManager(context)
        binding.favoriteRec.adapter = _favouriteAdapter
        binding.favoriteRec.isVisible = true
    }
    private fun initData() {
        val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
        ref.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                favouriteListMeals.clear()
                if (snapshot.exists()){
                    for (favouriteSnapshot in snapshot.children){
                        val favouriteModel = favouriteSnapshot.getValue(MealDetailModel::class.java)
                        if (favouriteModel != null) {
                            favouriteListMeals.add(favouriteModel)
                        }
                    }
//                    Log.d("check_null", "onDataChange: "+ favouriteListMeals)
                    _favouriteAdapter.setData(favouriteListMeals)
                    binding.shimmerFavouriteView.stopShimmer()
                    binding.shimmerFavouriteView.isVisible = false
                }
            }
            override fun onCancelled(error: DatabaseError) {
            }

        })
    }
}


