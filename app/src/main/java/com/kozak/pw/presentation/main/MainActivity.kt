package com.kozak.pw.presentation.main

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.kozak.pw.PwConstants
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityMainBinding
import com.kozak.pw.presentation.dashboard.DashboardActivity
import com.kozak.pw.presentation.num_composition.NumCompositionActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(),
    StartGameDialogFragment.StartGameDialogListener,
    AskDestroyCurrentWorldDialogFragment.AskDestroyCurrentWorldDialogListener {

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
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        // to refresh state of NewGame button after world was created
        viewModel.retrieveGameStarted()
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
            // check that if game is already been started
            var startedGameObserver: Observer<Boolean>? = null
            startedGameObserver = Observer<Boolean> { started ->
                if (started) {
                    AskDestroyCurrentWorldDialogFragment().show(
                        supportFragmentManager,
                        AskDestroyCurrentWorldDialogFragment.TAG_NAME
                    )
                } else {
                    showNewGameFragment()
                }
                Log.d(PwConstants.LOG_TAG, "startedGameObserver = $startedGameObserver")
                startedGameObserver?.let { viewModel.gameStarted.removeObserver(it) }
            }
            viewModel.gameStarted.observe(this, startedGameObserver)
            viewModel.retrieveGameStarted()
        }
        binding.buttonNumComposition.setOnClickListener {
            startActivity(NumCompositionActivity.intentStartGame(this))
        }
    }

    private fun showNewGameFragment() =
        StartGameDialogFragment().show(supportFragmentManager, StartGameDialogFragment.TAG_NAME)

    override fun onStartNewGameClick(gameSpeedIndex: Int) {
        startActivity(DashboardActivity.intentStartNewGame(this, gameSpeedIndex))
    }

    override fun onDestroyCurrentWorldClick() {
        viewModel.currentWorldDestroyed.observe(this) { dropped ->
            if (dropped) {
                showNewGameFragment()
            } else {
                Toast.makeText(this, R.string.failed_to_destroy_current_world, Toast.LENGTH_SHORT)
                    .show()
            }
            viewModel.currentWorldDestroyed.removeObservers(this)
        }
        viewModel.destroyCurrentWorld()
    }
}