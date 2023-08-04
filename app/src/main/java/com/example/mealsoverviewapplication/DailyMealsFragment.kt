package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.adapters.DailyMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentDailyMealsBinding
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.DailyMealsViewModel
import com.example.mealsoverviewapplication.viewmodels.RandomMealViewModel

class DailyMealsFragment : Fragment() {

    private val _dailyMealsAdapter by lazy {
        DailyMealsAdapter()
    }
    private val binding by lazy {
        FragmentDailyMealsBinding.inflate(layoutInflater)
    }
    private val dailyMealsViewModel by viewModels<DailyMealsViewModel> ()
    private val randomMealViewModel by viewModels<RandomMealViewModel>()

    var mealRandom: ArrayList<Meal> = arrayListOf()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initData()
        initAction()
    }
    private fun initAction() {
        _dailyMealsAdapter.setOnItemClickListener(object : DailyMealsAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val categoryId = _dailyMealsAdapter._dailyMealsArrayList[position].idCategory.toString()
                val title = _dailyMealsAdapter._dailyMealsArrayList[position].strCategory.toString()
                val thumb = _dailyMealsAdapter._dailyMealsArrayList[position].strCategoryThumb.toString()
                val description = _dailyMealsAdapter._dailyMealsArrayList[position].strCategoryDescription.toString()
                val mealDetail = MealDetail (title, thumb, description,categoryId)
                val direction = DailyMealsFragmentDirections.dailyMealsFragmentActionToViewDetailFragment(mealDetail)
                findNavController().navigate(direction)
            }
        })
        binding.makeItBtn.setOnClickListener {
            val randomMeal: Meal? = this.mealRandom.firstOrNull()
            val title = randomMeal?.strMeal.toString()
            val thumb = randomMeal?.strMealThumb.toString()
            val description = randomMeal?.strInstructions.toString()
            val mealDetail = MealDetail(title,thumb, description)
            val directions = DailyMealsFragmentDirections.dailyMealsFragmentActionToViewDetailFragment(mealDetail)
            findNavController().navigate(directions)
        }

        binding.imvSearch.setOnClickListener {
            findNavController().navigate(R.id.dailyMealsFragmentActionToSearchMealFragment)
        }

    }
    private fun initData() {
        dailyMealsViewModel.getCategory()
        dailyMealsViewModel.responseLiveData.observe(viewLifecycleOwner, Observer{ data ->
            _dailyMealsAdapter.setData(data as ArrayList<Category>)
        })

        randomMealViewModel.getRandomMeal()
        randomMealViewModel.responseLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null) {
                mealRandom.addAll(data)
            }
            initViewRandom()
        })
    }
    private fun initViewRandom() {
        var randomMeal: Meal? = this.mealRandom.firstOrNull()
        if (randomMeal != null) {
            binding.tvDesc.text = randomMeal.strMeal
            Glide.with(binding.shapeAbleImageView).load(randomMeal.strMealThumb).into(binding.shapeAbleImageView)
        }
    }
    private fun initView() {
        binding.recDailyMeal.layoutManager = LinearLayoutManager(context)
        binding.recDailyMeal.adapter = _dailyMealsAdapter
//        val type = dailyMealsViewModel.checkCategoryType("pho")
//        binding.makeItBtn.text = getString(type.title)
//        val typestring = "xxx"
//        if (typestring == "pho") {
//            binding.run {
//                makeItBtn.
//            }
//        }
    }
}