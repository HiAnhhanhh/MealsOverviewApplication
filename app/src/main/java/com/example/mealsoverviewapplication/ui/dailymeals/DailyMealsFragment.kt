package com.example.mealsoverviewapplication.ui.dailymeals

import android.content.Context
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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

class DailyMealsFragment  : Fragment() {

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
        setAnim()
    }

    private fun setAnim() {
        val animationZoomIn = AnimationUtils.loadAnimation(context, R.anim.zoomin_btn)
        binding.makeItBtn.startAnimation(animationZoomIn)

    }

    private fun initAction() {

        _dailyMealsAdapter.setOnItemClickListener(object : DailyMealsAdapter.OnItemClickListener{
            override fun onItemClick(data: CategoryModel, position: Int) {
                val category = data.strCategory.toString()
                val direction = DailyMealsFragmentDirections.dailyMealsFragmentActionToListMealsFragment(category)
                findNavController().navigate(direction)
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