package com.example.lab3

import android.os.Bundle
import android.view.MenuItem
import android.view.View

import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController

import com.example.lab3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

}