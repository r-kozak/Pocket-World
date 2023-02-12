package com.kozak.pw.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityDashboardBinding
import com.kozak.pw.domain.game.GameSpeed
import com.kozak.pw.presentation.news.NewsActivity
import com.kozak.pw.presentation.person.PersonsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    companion object {
        private const val IS_IT_START_OF_NEW_GAME: String = "is-new-game"
        private const val NEW_GAME_SPEED: String = "new-game-speed"

        fun intentStartNewGame(context: Context, gameSpeedIndex: Int): Intent {
            val intent = createIntent(context, true)
            intent.putExtra(NEW_GAME_SPEED, gameSpeedIndex)
            return intent
        }

        fun intentContinueGame(context: Context): Intent = createIntent(context, false)

        private fun createIntent(context: Context, isNewGame: Boolean): Intent {
            val intent = Intent(context, DashboardActivity::class.java)
            intent.putExtra(IS_IT_START_OF_NEW_GAME, isNewGame)
            return intent
        }
    }

    private var _binding: ActivityDashboardBinding? = null
    private val binding: ActivityDashboardBinding
        get() = _binding ?: throw RuntimeException("ActivityDashboardBinding == null")

    val viewModel: DashboardViewModel by viewModel()

    private var isStartNewGame: Boolean = false
    private var newGameSpeed: GameSpeed = GameSpeed.NORMAL

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        setClickListeners()
        playGame()
    }

    private fun playGame() {
        if (isStartNewGame) {
            viewModel.startNewGame(newGameSpeed)
        } else {
            viewModel.refreshPwState()
        }
        viewModel.failToStartNewGame.observe(this) { fail ->
            if (fail) {
                Toast.makeText(this, R.string.failed_to_start_new_game, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.failToRefreshPwState.observe(this) { fail ->
            if (fail) {
                Toast.makeText(this, R.string.failed_to_refresh_pw_state, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun parseIntent() {
        isStartNewGame = intent.getBooleanExtra(IS_IT_START_OF_NEW_GAME, false)

        val newGameSpeedIndex = intent.getIntExtra(NEW_GAME_SPEED, GameSpeed.defaultSpeedIndex())
        newGameSpeed = GameSpeed.values()[newGameSpeedIndex]
    }

    private fun setClickListeners() {
        binding.buttonPersons.setOnClickListener {
            startActivity(PersonsActivity.intentShowPersons(this))
        }
        binding.buttonRefreshPwState.setOnClickListener {
            viewModel.refreshPwState()
        }
        binding.buttonNews.setOnClickListener {
            startActivity(NewsActivity.startIntent(this))
        }
    }
}