package com.example.week11_pokeapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.week11_pokeapi.R
import com.example.week11_pokeapi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        with(binding) {
            val navController = findNavController(R.id.main_nav_host_fragment)
            bottomNavigationView.setupWithNavController(navController)
        }
    }
}