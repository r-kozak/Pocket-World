package com.kozak.pw.presentation.person

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.PwConstants.DEFAULT_ITEM_ID
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityPersonBinding

class PersonActivity : AppCompatActivity(), PersonFragment.OnEditingFinishedListener {
    private var currentPersonId: Long = DEFAULT_ITEM_ID
    private lateinit var binding: ActivityPersonBinding

    companion object {
        private const val INTENT_PERSON_ID = "intent_person_id"

        fun newEditPersonIntent(context: Context, personId: Long): Intent {
            val intent = Intent(context, PersonActivity::class.java)
            intent.putExtra(INTENT_PERSON_ID, personId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        parseIntent()

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

    override fun onEditingFinished() {
        Toast.makeText(this, R.string.person_saved_success, Toast.LENGTH_SHORT).show()
        finish()
    }
}