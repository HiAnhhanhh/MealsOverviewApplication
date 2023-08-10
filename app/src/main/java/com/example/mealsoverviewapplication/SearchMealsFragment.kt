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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealsoverviewapplication.adapters.FilterMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentSearchMealsBinding
import com.example.mealsoverviewapplication.mapper.MealDetailModel
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
        initAction()
    }

    private fun initObserver() {
        filterMealsViewModel.responseLiveData.observe(viewLifecycleOwner) { data ->
            if (data != null){
                filterMealsAdapter.setData(data as ArrayList<MealDetailModel>)
            }
        }
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
        filterMealsAdapter.setOnItemClickListener(object : FilterMealsAdapter.OnItemClickListener {
            override fun onItemClick(data:MealDetailModel, position: Int) {
                val mealId = data.idMeal.toString()
                val direction = SearchMealsFragmentDirections.searchMealsFragmentActionToViewDetailOfMealFragment(mealId)
                findNavController().navigate(direction)
            }
        })
    }
    private fun filterMealsList(s: Editable?) {
        if (!s.isNullOrEmpty()){
            filterMealsViewModel.getFilterMeals(s)
            binding.recList.isVisible = true
        } else{
            binding.recList.isVisible = false
            filterMealsAdapter.clearData()
        }
    }
}


