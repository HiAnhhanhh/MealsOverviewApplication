package com.example.mealsoverviewapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.mealsoverviewapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


//        val navController = this.findNavController(R.id.fragmentContainer)
//
//        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
//        navView.setupWithNavController(navController)

        goToFragment(DailyMealsFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.home -> goToFragment(DailyMealsFragment())
                R.id.saved -> goToFragment(FavoriteListMealsFragment())
                R.id.profile -> goToFragment(ProfileFragment())
                else -> {
                }
            }
            true
        }

    }

    private fun goToFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager.beginTransaction().replace(R.id.fragmentContainer,fragment).commit()
    }


}
