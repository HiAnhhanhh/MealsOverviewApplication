package com.example.mealsoverviewapplication.ui.dailymeals

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.ui.utils.Constants
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.FragmentDailyMealsBinding
import com.example.mealsoverviewapplication.data.mapper.CategoryModel
import com.example.mealsoverviewapplication.data.mapper.MealDetailModel
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.text.DateFormat
import java.util.*
import kotlin.collections.ArrayList

class DailyMealsFragment : Fragment() {

    private val _dailyMealsAdapter by lazy {
        DailyMealsAdapter()
    }
    private val binding by lazy {
        FragmentDailyMealsBinding.inflate(layoutInflater)
    }
    private val dailyMealsViewModel by viewModels<DailyMealsViewModel> ()
    private val randomMealViewModel by viewModels<RandomMealViewModel>()

    private var mealRandom: ArrayList<MealDetailModel> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObserve()
        initView()
        initAction()
    }
    private fun initAction() {

        _dailyMealsAdapter.setOnItemClickListener(object : DailyMealsAdapter.OnItemClickListener{
            override fun onItemClick(data: CategoryModel, position: Int) {
                val category = data.strCategory.toString()
                val direction = DailyMealsFragmentDirections.dailyMealsFragmentActionToListMealsFragment(category)
                findNavController().navigate(direction)
            }
            override fun onItemClickFavorite(data: CategoryModel, position: Int) {
                val check = "true"
                val timeStamp = System.currentTimeMillis().toString()
                val hashMap: HashMap<String, Any> = HashMap()
                hashMap[Constants.STR_CATEGORY_ID] = data.categoryId.toString()
                hashMap[Constants.STR_CATEGORY] = data.strCategory.toString()
                hashMap[Constants.STR_CATEGORY_THUMB] = data.strCategoryThumb.toString()
                hashMap[Constants.DESCRIPTION] = data.strCategoryDes.toString()
                hashMap[Constants.TIMESTAMP] = timeStamp
                hashMap[Constants.CHECK]= check

                val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
                ref.child(data.categoryId.toString()).setValue(hashMap)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener { e ->
                        Log.d("check_false", "insertData: "+ e.message)
                    }
            }
        })
        binding.makeItBtn.setOnClickListener {
            val randomMeal : MealDetailModel? = this.mealRandom.firstOrNull()
            val mealId = randomMeal?.idMeal.toString()
            val directions = DailyMealsFragmentDirections.dailyMealsFragmentActionToViewDetailFragment(mealId)
            findNavController().navigate(directions)
        }

        binding.imvSearch.setOnClickListener {
            findNavController().navigate(R.id.dailyMealsFragmentActionToSearchMealFragment)
        }

    }
    private fun initObserve() {
        dailyMealsViewModel.getCategory()
        dailyMealsViewModel.responseLiveData.observe(viewLifecycleOwner) { data ->
            _dailyMealsAdapter.setData(data as ArrayList<CategoryModel>)
            binding.shimmerViewContainer.stopShimmer()
            binding.shimmerViewContainer.isVisible = false
            binding.tvSeeAll.isVisible = true
        }

        randomMealViewModel.getRandomMeal()
        randomMealViewModel.responseLiveData.observe(viewLifecycleOwner) { data ->
            if (data != null) {
                mealRandom.addAll(data)
            }
            initViewRandom()
        }
    }
    private fun initViewRandom() {
        val randomMeal: MealDetailModel? = this.mealRandom.firstOrNull()
        if (randomMeal != null) {
            binding.tvDesc.text = randomMeal.strMeal
            Glide.with(binding.shapeAbleImageView).load(randomMeal.strThumb).into(binding.shapeAbleImageView)
        }
        binding.progressBar.isVisible = false
    }
    private fun initView() {
        binding.shimmerViewContainer.startShimmer()
        val calendar: Calendar = Calendar.getInstance()
        val currentDate : String  = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        binding.tvDate.text = currentDate
        binding.recDailyMeal.layoutManager = LinearLayoutManager(context)
        binding.recDailyMeal.adapter = _dailyMealsAdapter
        binding.recDailyMeal.isVisible = true
    }
}