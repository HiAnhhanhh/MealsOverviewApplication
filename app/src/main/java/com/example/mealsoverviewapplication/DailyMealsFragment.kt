package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.adapters.DailyMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentDailyMealsBinding
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.DailyMealsViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView

class DailyMealsFragment : Fragment() {

    private val check: String = "CHECK"
    private val _dailyMealsAdapter by lazy {
        DailyMealsAdapter()
    }
    private val binding by lazy {
        FragmentDailyMealsBinding.inflate(layoutInflater)
    }
    lateinit var dailyMealsViewModel : DailyMealsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imvSearch.setOnClickListener {
            findNavController().navigate(R.id.dailyMealsFragmentActionToSearchMealFragment)
        }

        initRecyclerView()
        initData()
        goToViewDetail()
    }
    private fun goToViewDetail() {
        _dailyMealsAdapter.setOnItemClickListener(object : DailyMealsAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val title = _dailyMealsAdapter._dailyMealsArrayList[position].strCategory.toString()
                val thumb = _dailyMealsAdapter._dailyMealsArrayList[position].strCategoryThumb.toString()
                val description = _dailyMealsAdapter._dailyMealsArrayList[position].strCategoryDescription.toString()
                val mealDetail = MealDetail (title, thumb, description)
                val direction = DailyMealsFragmentDirections.dailyMealsFragmentActionToViewDetailFragment(mealDetail)
                findNavController().navigate(direction)
            }
        })
    }

    private fun initData() {
        dailyMealsViewModel = ViewModelProvider(this)[DailyMealsViewModel::class.java]
        dailyMealsViewModel.getCategory()
        dailyMealsViewModel.responseLiveData.observe(viewLifecycleOwner, Observer{
            _dailyMealsAdapter.setData(it as ArrayList<Category>)
            Log.d("check_data", "onViewCreated: 12"+ it)
        })
    }

    private fun initRecyclerView() {
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