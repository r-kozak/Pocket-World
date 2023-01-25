package com.kozak.pw.presentation.num_composition

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.databinding.ActivityNumCompositionBinding

class NumCompositionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNumCompositionBinding

    companion object {
        fun intentStartGame(context: Context): Intent {
            return Intent(context, NumCompositionActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNumCompositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
    }
}