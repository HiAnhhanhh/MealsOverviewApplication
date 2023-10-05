package com.example.mealsoverviewapplication.ui.profile

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.airbnb.lottie.LottieDrawable
import com.example.mealsoverviewapplication.R
import com.example.mealsoverviewapplication.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
     val binding by lazy {
         FragmentProfileBinding.inflate(layoutInflater)
     }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupAnim()
    }

    private fun setupAnim() {
        binding.animationView.setAnimation(R.raw.animation_llxgo9py)
        binding.animationView.repeatCount = LottieDrawable.INFINITE
        binding.animationView.playAnimation()
    }
}