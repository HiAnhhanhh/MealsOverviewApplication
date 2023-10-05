package com.example.mealsoverviewapplication.ui.mainactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ActivityMainBinding
import com.example.mealsoverviewapplication.ui.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Log.d(Constants.CHECK_LIFECYCLE, "onCreate: ")


        val navController = this.findNavController(R.id.fragmentContainer)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.listMealsFragment -> {
                    binding.bottomNavigationView.isVisible = false
                }
                R.id.searchMealsFragment -> {
                    binding.bottomNavigationView.isVisible = false
                }
                else -> binding.bottomNavigationView.isVisible = destination.id != R.id.viewDetailOfMealFragment
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(Constants.CHECK_LIFECYCLE, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(Constants.CHECK_LIFECYCLE, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(Constants.CHECK_LIFECYCLE, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(Constants.CHECK_LIFECYCLE, "onStop: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(Constants.CHECK_LIFECYCLE, "onRestart: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(Constants.CHECK_LIFECYCLE, "onDestroy: ")
    }

}
