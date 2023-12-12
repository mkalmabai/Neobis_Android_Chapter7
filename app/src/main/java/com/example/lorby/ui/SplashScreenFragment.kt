package com.example.lorby.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.lorby.R

class SplashScreenFragment : Fragment() {
    private val delayMillis = 1500
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Handler(
            Looper.getMainLooper()).postDelayed({
                findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment2)
        }, delayMillis.toLong())
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

}