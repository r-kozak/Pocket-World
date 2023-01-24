package com.kozak.pw.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.databinding.ActivityMainBinding
import com.kozak.pw.presentation.num_composition.NumCompositionActivity
import com.kozak.pw.presentation.person.PersonsActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonPersons.setOnClickListener {
            startActivity(PersonsActivity.intentShowPersons(this))
        }

        binding.buttonNumComposition.setOnClickListener {
            startActivity(NumCompositionActivity.intentStartGame(this))
        }
    }
}