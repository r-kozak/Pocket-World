package com.kozak.pw.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kozak.pw.PwConstants
import com.kozak.pw.databinding.ActivityPersonsBinding
import com.kozak.pw.presentation.view_model.PersonsViewModel

class PersonsActivity : AppCompatActivity() {
    private lateinit var viewModel: PersonsViewModel
    private lateinit var binding: ActivityPersonsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[PersonsViewModel::class.java]
        viewModel.personItemsList.observe(this) {
            Log.d(PwConstants.PW_LOG_TAG, it.toString())
        }

        binding.buttonKill.setOnClickListener {
            val id = (1..10).random().toLong()
            Log.d(PwConstants.PW_LOG_TAG, "Killed person: $id")
            viewModel.killPerson(id)
        }
    }
}