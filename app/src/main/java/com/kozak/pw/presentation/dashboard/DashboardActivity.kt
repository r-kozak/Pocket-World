package com.kozak.pw.presentation.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityDashboardBinding
import com.kozak.pw.presentation.news.NewsActivity
import com.kozak.pw.presentation.person.PersonsActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    companion object {
        fun intentStartGame(context: Context): Intent {
            return Intent(context, DashboardActivity::class.java)
        }
    }

    private var _binding: ActivityDashboardBinding? = null
    private val binding: ActivityDashboardBinding
        get() = _binding ?: throw RuntimeException("ActivityDashboardBinding == null")

    val viewModel: DashboardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.failToGeneratePerson.observe(this) { fail ->
            if (fail) {
                Toast.makeText(this, R.string.failed_to_refresh_pw_state, Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.refreshPwState()
        setClickListeners()
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