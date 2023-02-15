package com.kozak.pw.presentation.person

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kozak.pw.PwConstants
import com.kozak.pw.databinding.FragmentPersonBinding

class PersonFragment : Fragment() {

    private lateinit var onEditingFinishedListener: OnEditingFinishedListener
    private var currentPersonId: Long = PwConstants.DEFAULT_ITEM_ID

    private val viewModel by lazy {
        val viewModelFactory = PersonViewModelFactory(
            requireActivity().application,
            currentPersonId
        )
        ViewModelProvider(this, viewModelFactory)[PersonViewModel::class.java]
    }

    private var _binding: FragmentPersonBinding? = null
    private val binding: FragmentPersonBinding
        get() = _binding ?: throw RuntimeException("FragmentPersonBinding == null")

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

    interface OnEditingFinishedListener {
        fun onEditingFinished()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Context must implement OnEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPersonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.shouldCloseScreen.observe(viewLifecycleOwner) {
            onEditingFinishedListener.onEditingFinished()
        }
    }

    override fun onStart() {
        super.onStart()
        setClickListeners()
        addTextChangeListeners()
        addOnCheckedListeners()
    }

    private fun addOnCheckedListeners() {
        binding.cbIsFavorite.apply {
            setOnCheckedChangeListener { _, isChecked ->
                viewModel.person.value?.isFavorite = isChecked
            }
        }
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
            viewModel.editPerson(
                currentPersonId,
                binding.etFirstName.editableText.toString(),
                binding.etLastName.editableText.toString(),
                binding.etStrength.editableText.toString(),
                binding.cbIsFavorite.isChecked
            )
        }
    }

    private fun addTextChangeListeners() {
        binding.etFirstName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputFirstName()
                viewModel.person.value?.firstName = s.toString()
                Log.d(PwConstants.LOG_TAG, "etFirstName.onTextChanged()")
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.etLastName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputLastName()
                viewModel.person.value?.lastName = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        binding.etStrength.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.resetErrorInputStrength()
                viewModel.person.value?.strength = s.toString().toInt()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}