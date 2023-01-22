package com.kozak.pw.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}