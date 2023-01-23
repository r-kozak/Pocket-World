package com.kozak.pw.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kozak.pw.PwConstants
import com.kozak.pw.PwConstants.DEFAULT_ITEM_ID
import com.kozak.pw.databinding.ActivityPersonBinding
import com.kozak.pw.presentation.fragment.PersonFragment
import com.kozak.pw.presentation.view_model.PersonViewModel

class PersonActivity : AppCompatActivity() {
    private var currentPersonId: Long = DEFAULT_ITEM_ID
    private lateinit var binding: ActivityPersonBinding
    private lateinit var viewModel: PersonViewModel


    companion object {
        private const val INTENT_PERSON_ID = "intent_person_id"

        fun newEditPersonIntent(context: Context, personItemId: Long): Intent {
            val intent = Intent(context, PersonActivity::class.java)
            intent.putExtra(INTENT_PERSON_ID, personItemId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()

        viewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        Log.d(PwConstants.LOG_TAG, "PersonActivity - viewModel: $viewModel")

        if (savedInstanceState == null) {
            // this is the first activity creation
            launchFragment()
        }
    }

    private fun launchFragment() {
        supportFragmentManager.beginTransaction()
            .replace(binding.personContainer.id, PersonFragment.newInstance(currentPersonId))
            .commit()
    }

    private fun parseIntent() {
        currentPersonId = intent.getLongExtra(INTENT_PERSON_ID, DEFAULT_ITEM_ID)
        if (currentPersonId == DEFAULT_ITEM_ID) {
            throw RuntimeException("Param person id is absent")
        }
    }
}