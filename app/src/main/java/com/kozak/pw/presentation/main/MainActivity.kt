package com.kozak.pw.presentation.main

import android.Manifest
import android.app.ActivityOptions
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.kozak.pw.PwConstants
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityMainBinding
import com.kozak.pw.domain.game.GameSpeed
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
        setupObservers()
    }

    private fun setupObservers() {
        // observe event of starting a new game. After successful starting - go to DashboardActivity,
        // but when starting failed - show Toast with message
        viewModel.startNewGameResult.observe(this) {
            it?.let { success ->
                if (success) startActivity(DashboardActivity.createIntent(this))
                else showToast(R.string.failed_to_start_new_game)

                // prevent reusing startNewGameResult value after activity will be recreated
                viewModel.afterUseStartNewGameResult()
            }
        }
        // destroying the current world can be called only on creating a new game, when game is
        // already created - we ask - destroy or not destroy.
        // observe event of destroying current world. After successful destroying - show fragment
        // with initial settings, but when destroying failed - show Toast with message
        viewModel.destroyCurrentWorldResult.observe(this) {
            it?.let { dropped ->
                if (dropped) showNewGameFragment()
                else showToast(R.string.failed_to_destroy_current_world)

                // prevent reusing destroyCurrentWorldResult value after activity will be recreated
                viewModel.afterUseDestroyCurrentWorldResult()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // to refresh state of NewGame button after world was created and we return back from
        // DashboardActivity to this - MainActivity
        viewModel.refreshGameStarted()
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
            val options = ActivityOptions.makeClipRevealAnimation(it, 0, 0, it.width, it.height)
            startActivity(DashboardActivity.createIntent(this), options.toBundle())
        }
        binding.buttonNewGame.setOnClickListener {
            // check that if game is already been started
            if (viewModel.gameStarted.value == true) {
                AskDestroyCurrentWorldDialogFragment().show(
                    supportFragmentManager,
                    AskDestroyCurrentWorldDialogFragment.TAG_NAME
                )
            } else {
                showNewGameFragment()
            }
        }
        binding.buttonNumComposition.setOnClickListener {
            startActivity(NumCompositionActivity.intentStartGame(this))
        }
    }

    private fun showNewGameFragment() =
        StartGameDialogFragment().show(supportFragmentManager, StartGameDialogFragment.TAG_NAME)

    private fun showToast(stringResId: Int) {
        Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show()
    }

    override fun onStartNewGameClick(gameSpeedIndex: Int) {
        viewModel.startNewGame(GameSpeed.values()[gameSpeedIndex])
    }

    override fun onDestroyCurrentWorldClick() {
        viewModel.destroyCurrentWorld()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.startNewGameResult.removeObservers(this)
        viewModel.destroyCurrentWorldResult.removeObservers(this)
    }
}