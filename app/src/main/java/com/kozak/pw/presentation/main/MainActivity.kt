package com.kozak.pw.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kozak.pw.databinding.ActivityMainBinding
import com.kozak.pw.presentation.CoroutinesActivity
import com.kozak.pw.presentation.num_composition.NumCompositionActivity
import com.kozak.pw.presentation.person.PersonsActivity

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        setupClickListeners()
        viewModel.refreshPwState()
    }

    private fun setupClickListeners() {
        binding.buttonPersons.setOnClickListener {
            startActivity(PersonsActivity.intentShowPersons(this))
        }
        binding.buttonNumComposition.setOnClickListener {
            startActivity(NumCompositionActivity.intentStartGame(this))
        }
        binding.buttonCoroutines.setOnClickListener {
            startActivity(CoroutinesActivity.startIntent(this))
        }
        binding.buttonRefreshPwState.setOnClickListener {
            viewModel.refreshPwState()
        }
    }
}