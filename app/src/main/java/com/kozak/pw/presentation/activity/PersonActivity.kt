package com.kozak.pw.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kozak.pw.PwConstants.DEFAULT_ITEM_ID
import com.kozak.pw.databinding.ActivityPersonBinding
import com.kozak.pw.presentation.fragment.PersonFragment

class PersonActivity : AppCompatActivity() {
    private var currentPersonId: Long = DEFAULT_ITEM_ID
    private lateinit var binding: ActivityPersonBinding

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
        supportFragmentManager.beginTransaction()
            .add(binding.personContainer.id, PersonFragment.newInstance(currentPersonId))
            .commit()
    }

    private fun parseIntent() {
        currentPersonId = intent.getLongExtra(INTENT_PERSON_ID, DEFAULT_ITEM_ID)
        if (currentPersonId == DEFAULT_ITEM_ID) {
            throw RuntimeException("Param person id is absent")
        }
    }
}