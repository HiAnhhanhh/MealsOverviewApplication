package com.example.mealsoverviewapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.adapters.FilterMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentSearchMealsBinding
import com.example.mealsoverviewapplication.models.Meal
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.FilterMealsViewModel
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
        initData()
        initAction()
    }

    private fun initObserver() {
        filterMealsViewModel.responseLiveData.observe(viewLifecycleOwner, Observer { data ->
            if (data != null){
                filterMealsAdapter.setData(data as ArrayList<Meal>)
            }else {
                filterMealsAdapter._filterMealsArrayList.clear()
            }
        })
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

        filterMealsAdapter.setOnItemClickListener(object : FilterMealsAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val title = filterMealsAdapter._filterMealsArrayList[position].strCategory.toString()
                val thumb = filterMealsAdapter._filterMealsArrayList[position].strMealThumb.toString()
                val description = filterMealsAdapter._filterMealsArrayList[position].strInstructions.toString()
                val mealDetail = MealDetail(title, thumb, description)
                val direction = SearchMealsFragmentDirections.searchMealsFragmentActionToViewDetailOfMealFragment(mealDetail)
                findNavController().navigate(direction)
            }
        })
    }

    private fun filterMealsList(s: Editable?) {
        if (!s.isNullOrEmpty()){
            filterMealsViewModel.getFilterMeals(s)
            binding.recList.isVisible = true
        } else{
            filterMealsAdapter._filterMealsArrayList.clear()
            binding.recList.isVisible = false
        }
    }
    private fun initData() {

    }
}


