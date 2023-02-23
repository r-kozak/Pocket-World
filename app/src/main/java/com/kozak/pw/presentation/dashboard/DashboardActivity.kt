package com.kozak.pw.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.databinding.ActivityDashboardBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)

        val drawView = HexagonMaskView(this)
//        val params: ViewGroup.LayoutParams = ViewGroup.LayoutParams(2000, 2000)
//        binding.map.addView(drawView, params)
        setContentView(drawView/*binding.root*/)
    }
}