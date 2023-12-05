package com.example.lorby.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lorby.R
import com.example.lorby.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}