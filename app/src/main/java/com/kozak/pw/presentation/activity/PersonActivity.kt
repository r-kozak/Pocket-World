package com.kozak.pw.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.kozak.pw.PwConstants.DEFAULT_ITEM_ID
import com.kozak.pw.R
import com.kozak.pw.databinding.ActivityPersonBinding
import com.kozak.pw.presentation.view_model.PersonViewModel
import com.kozak.pw.short

class PersonActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPersonBinding
    private lateinit var viewModel: PersonViewModel
    private var currentPersonId: Long? = null

    companion object {
        private const val INTENT_PERSON_ID = "intent_person_id"

        fun intentEditPerson(context: Context, personItemId: Long): Intent {
            val intent = Intent(context, PersonActivity::class.java)
            intent.putExtra(INTENT_PERSON_ID, personItemId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseIntent()

        binding = ActivityPersonBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        setClickListeners()
        addTextChangeListeners()
        displayPersonData()
        observeViewModel()
    }

    private fun displayPersonData() {
        currentPersonId?.let { id ->
            viewModel.getPersonItem(id)
            viewModel.personItem.observe(this) {
                binding.etFirstName.setText(it.firstName)
                binding.etLastName.setText(it.lastName)
                binding.etStrength.setText(it.strength.toString())
                binding.tvBirthDate.text = it.birthDate.short()
                binding.tvSex.text = it.sex.toString()
                binding.tvDeathDate.text = it.deathDate.short()
                binding.tvIsAlive.text = it.isAlive.toString()
                binding.tvIsFavorite.text = it.isFavorite.toString()
                binding.tvId.text = it.id.toString()
            }
        } ?: throw RuntimeException("Person ID is not defined!")
    }

    private fun addTextChangeListeners() {
        binding.etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputFirstName()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.etLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputLastName()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
        binding.etStrength.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputStrength()
            }
            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun parseIntent() {
        currentPersonId = intent.getLongExtra(INTENT_PERSON_ID, DEFAULT_ITEM_ID)
            .let { if (it == DEFAULT_ITEM_ID) null else it }
    }

    private fun setClickListeners() {
        // Kill button
        binding.killButton.setOnClickListener {
            currentPersonId?.let {
                viewModel.killPerson(it)
            } ?: throw RuntimeException("Person ID is not defined!")
        }
        // Save button
        binding.saveButton.setOnClickListener {
            currentPersonId?.let { personId ->
                viewModel.editPersonItem(
                    personId,
                    binding.etFirstName.editableText.toString(),
                    binding.etLastName.editableText.toString(),
                    binding.etStrength.editableText.toString()
                )
            } ?: throw RuntimeException("Person ID is not defined!")
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputFirstName.observe(this) { isError ->
            binding.tilFirstName.error = if (isError) getString(R.string.error_input_first_name)
            else null
        }
        viewModel.errorInputLastName.observe(this) { isError ->
            binding.tilLastName.error = if (isError) getString(R.string.error_input_last_name)
            else null
        }
        viewModel.errorInputStrength.observe(this) { isError ->
            binding.tilStrength.error = if (isError) getString(R.string.error_input_strength)
            else null
        }
        viewModel.shouldCloseScreen.observe(this) {
            finish()
        }
    }
}