package com.dart69.simpleweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dart69.simpleweatherapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}