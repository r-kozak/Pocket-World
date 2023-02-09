package com.kozak.pw.presentation.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kozak.pw.PwConstants
import com.kozak.pw.databinding.ActivityMainBinding
import com.kozak.pw.presentation.dashboard.DashboardActivity
import com.kozak.pw.presentation.num_composition.NumCompositionActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), StartGameDialogFragment.StartGameDialogListener {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding ?: throw RuntimeException("ActivityMainBinding == null")

    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        requestPwPermissions()

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.retrieveAppVersion()
        viewModel.retrieveGameStarted()
        setupClickListeners()
    }

    private fun requestPwPermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                Log.d(PwConstants.LOG_TAG, "No permissions to show notifications")

                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                    PwConstants.PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    private fun setupClickListeners() {
        binding.buttonContinue.setOnClickListener {
            startActivity(DashboardActivity.intentContinueGame(this))
        }
        binding.buttonNewGame.setOnClickListener {
            val startGameFragment = StartGameDialogFragment()
            startGameFragment.show(supportFragmentManager, "start-game")
        }
        binding.buttonNumComposition.setOnClickListener {
            startActivity(NumCompositionActivity.intentStartGame(this))
        }
    }

    override fun onStartNewGameClick(gameSpeedIndex: Int) {
        startActivity(DashboardActivity.intentStartNewGame(this, gameSpeedIndex))
    }
}