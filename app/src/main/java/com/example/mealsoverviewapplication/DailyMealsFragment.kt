package com.example.mealsoverviewapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.adapters.DailyMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentDailyMealsBinding
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.viewmodels.DailyMealsViewModel
import com.example.mealsoverviewapplication.viewmodels.RandomMealsViewModel
import kotlin.math.log

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

        initRecyclerView()

        dailyMealsViewModel = ViewModelProvider(this)[DailyMealsViewModel::class.java]
        dailyMealsViewModel.getCategory()
        dailyMealsViewModel.responseLiveData.observe(this, Observer{
            _dailyMealsAdapter.setData(it as ArrayList<Category>)
            Log.d("check_data", "onViewCreated: 12"+ it)
        })
    }

    private fun initAction() {

    }

    private fun initData() {

    }

    private fun observeData() {

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