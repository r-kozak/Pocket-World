package com.kozak.pw.presentation.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kozak.pw.PwConstants
import com.kozak.pw.R
import com.kozak.pw.databinding.FragmentPersonBinding
import com.kozak.pw.presentation.view_model.PersonViewModel
import com.kozak.pw.short

class PersonFragment : Fragment() {

    private lateinit var viewModel: PersonViewModel
    private lateinit var binding: FragmentPersonBinding
    private var currentPersonId: Long = PwConstants.DEFAULT_ITEM_ID

    companion object {
        private const val PERSON_ID = "person_id"

        fun newInstance(personId: Long): PersonFragment {
            return PersonFragment().apply {
                arguments = Bundle().apply {
                    putLong(PERSON_ID, personId)
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
         binding = FragmentPersonBinding.inflate(inflater, container, false)
         return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[PersonViewModel::class.java]
        setClickListeners()
        addTextChangeListeners()
        displayPersonData()
        observeViewModel()
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(PERSON_ID)) throw RuntimeException("Param person id is absent")
        currentPersonId = args.getLong(PERSON_ID, PwConstants.DEFAULT_ITEM_ID)
    }

    private fun setClickListeners() {
        // Kill button
        binding.killButton.setOnClickListener {
            viewModel.killPerson(currentPersonId)
        }
        // Save button
        binding.saveButton.setOnClickListener {
            viewModel.editPersonItem(
                currentPersonId,
                binding.etFirstName.editableText.toString(),
                binding.etLastName.editableText.toString(),
                binding.etStrength.editableText.toString()
            )
        }
    }

    private fun observeViewModel() {
        viewModel.errorInputFirstName.observe(viewLifecycleOwner) { isError ->
            binding.tilFirstName.error = if (isError) getString(R.string.error_input_first_name)
            else null
        }
        viewModel.errorInputLastName.observe(viewLifecycleOwner) { isError ->
            binding.tilLastName.error = if (isError) getString(R.string.error_input_last_name)
            else null
        }
        viewModel.errorInputStrength.observe(viewLifecycleOwner) { isError ->
            binding.tilStrength.error = if (isError) getString(R.string.error_input_strength)
            else null
        }
        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            activity?.onBackPressedDispatcher?.onBackPressed()
        }
    }

    private fun displayPersonData() {
        viewModel.getPersonItem(currentPersonId)
        viewModel.personItem.observe(viewLifecycleOwner) {
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
}