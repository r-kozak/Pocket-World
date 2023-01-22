package com.kozak.pw.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPersons.setOnClickListener {
            startActivity(PersonsActivity.intentShowPersons(this))
        }
    }
}