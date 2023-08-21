package com.example.mealsoverviewapplication.ui.mainactivities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
}
