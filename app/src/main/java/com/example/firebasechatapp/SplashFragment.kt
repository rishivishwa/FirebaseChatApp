package com.example.firebasechatapp

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.firebasechatapp.databinding.ActivityMainBinding
import com.example.firebasechatapp.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding : FragmentSplashBinding
    private val splashDuration: Long = 2000

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =  FragmentSplashBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Handler().postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_contactFragment)
        }, splashDuration)
    }

}