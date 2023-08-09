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
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.mealsoverviewapplication.adapters.DailyMealsAdapter
import com.example.mealsoverviewapplication.databinding.FragmentDailyMealsBinding
import com.example.mealsoverviewapplication.models.Category
import com.example.mealsoverviewapplication.models.MealDetail
import com.example.mealsoverviewapplication.viewmodels.DailyMealsViewModel
import com.example.mealsoverviewapplication.viewmodels.RandomMealViewModel
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

    private var mealRandom: ArrayList<MealDetail> = arrayListOf()
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
            override fun onItemClick(data:Category, position: Int) {
                val category = data.strCategory
                val direction = DailyMealsFragmentDirections.dailyMealsFragmentActionToListMealsFragment(category)
                findNavController().navigate(direction)
            }

            override fun onItemClickFavorite(data:Category, position: Int) {
                val check = "true"
                val timeStamp = System.currentTimeMillis().toString()
                val hashMap: HashMap<String, Any> = HashMap()
                hashMap[Constants.STR_CATEGORY_ID] = data.idCategory
                hashMap[Constants.STR_CATEGORY] = data.strCategory
                hashMap[Constants.STR_CATEGORY_THUMB] = data.strCategoryThumb
                hashMap[Constants.DESCRIPTION] = data.strCategoryDescription
                hashMap[Constants.TIMESTAMP] = timeStamp
                hashMap[Constants.CHECK]= check

                val ref: DatabaseReference = FirebaseDatabase.getInstance().getReference("FavouritesList")
                ref.child(data.idCategory).setValue(hashMap)
                    .addOnSuccessListener {
                    }
                    .addOnFailureListener { e ->
                        Log.d("check_false", "insertData: "+ e.message)
                    }
            }
        })
        binding.makeItBtn.setOnClickListener {
            val randomMeal : MealDetail? = this.mealRandom.firstOrNull()
            val mealId = randomMeal?.idMeal.toString()
            val directions = DailyMealsFragmentDirections.dailyMealsFragmentActionToViewDetailFragment(mealId)
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
        val randomMeal: MealDetail? = this.mealRandom.firstOrNull()
        if (randomMeal != null) {
            binding.tvDesc.text = randomMeal.strMeal
            Glide.with(binding.shapeAbleImageView).load(randomMeal.strMealThumb).into(binding.shapeAbleImageView)
        }
    }
    private fun initView() {
        val calendar: Calendar = Calendar.getInstance()
        val currentDate : String  = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.time)
        binding.tvDate.text = currentDate

        binding.recDailyMeal.layoutManager = LinearLayoutManager(context)
        binding.recDailyMeal.adapter = _dailyMealsAdapter

    }
}